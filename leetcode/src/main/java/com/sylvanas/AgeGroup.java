package com.sylvanas;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * 题目如下
 * <p>
 * 1000万用户数据存储在info.txt中格式如下：
 * id          name    age
 * 000000001  刘德华    20
 * 000000002  周杰伦    21
 * ……
 * <p>
 * 说明，id、name非空，age有可能为空。
 * <p>
 * 写一段Java程序，尽可能快统计出每个年龄的用户各有多少人，将结果存储在result.txt
 */
public class AgeGroup {
  private Logger logger = Logger.getLogger("ageGroup");
  /**
   * 结果集
   */
  private Map<String, LongAdder> ageToCntMap = new ConcurrentHashMap<>();
  List<Future<Map<String, LongAdder>>> futureList = new ArrayList<>();

  /**
   * 用来控制主线程什么时候可以结束
   */
  private CountDownLatch countDownLatch;


  private ExecutorService executor = new ThreadPoolExecutor(10,
      10,
      0L,
      TimeUnit.MILLISECONDS,
      new LinkedBlockingQueue<>(2000),
      new NamedThreadFactory("age-group"),
      (task, executor) -> logger.info("task is rejected!")
  );


  private void readFile(String filePath) throws FileNotFoundException, InterruptedException, ExecutionException {
    logger.info("starting to group file data...");
    File file = new File(filePath);
    long fileLength = file.length();
    int regionSize = 1024;
    int batchNum = (int) Math.ceil(fileLength * 1.0 / regionSize);
    countDownLatch = new CountDownLatch(batchNum);
    int regionIndex = 0;
    while (regionIndex < batchNum) {
      futureList.add(regionRead(filePath, regionIndex++, regionSize));
    }

  }


  private Future<Map<String, LongAdder>> regionRead(String filePath, int regionIndex, int regionSize) throws ExecutionException, InterruptedException {
    return executor.submit(() -> {
      Map<String, LongAdder> subAgeToCntMap = new HashMap<>();
      try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
        byte[] region = new byte[regionSize];
        long posOffset = (long) regionIndex * regionSize;
        raf.seek(posOffset);
        int actualSize = raf.read(region);
        // 没有内容
        if (actualSize < 0) {
          logger.info("region is empty!");
          return Collections.emptyMap();
        }
        // 清理多余空间
        if (actualSize < regionSize) {
          byte[] actualRegion = new byte[actualSize];
          System.arraycopy(region, 0, actualRegion, 0, actualSize);
          region = actualRegion;
        }
        // 处理文件块
        extractRegionData(subAgeToCntMap, filePath, region, posOffset);
      } catch (Exception e) {
        logger.log(Level.WARNING, "read file error,region index is:" + regionIndex, e);
      } finally {
        // 不管任务是否真的执行完 都默认文件块被处理
        countDownLatch.countDown();
        logger.info("task finished!");
      }
      return subAgeToCntMap;
    });
  }

  private void extractRegionData(Map<String, LongAdder> ageToCntMap, String filePath, byte[] region, long posOffset) {
    try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
      int firstNewLineIndex = findRegionFirstLineEndIndex(region);
      // 可能是最后一行 可能是文件只有一行
      if (firstNewLineIndex < -1) {
        readLine(ageToCntMap, raf);
        return;
      }
      int lineNo = countRegionLines(region);
      // 移到区间开始处
      raf.seek(posOffset + firstNewLineIndex + 1);
      while (lineNo-- > 0) {
        readLine(ageToCntMap, raf);
      }
    } catch (Exception e) {
      logger.log(Level.WARNING, "extract region error", e);
    }
  }

  private void readLine(Map<String, LongAdder> ageToCntMap, RandomAccessFile raf) throws IOException {
    String data = raf.readLine();
    if (data != null) {
      data = new String(data.getBytes(StandardCharsets.ISO_8859_1));
      count(ageToCntMap, data);
    }
  }

  /**
   * 查找一个region从指定位置之后的换行符位置
   */
  private int findRegionFirstLineEndIndex(byte[] region) {
    for (int i = 0; i < region.length; i++) {
      if (region[i] == "\n".getBytes()[0]) {
        return i;
      }
    }
    return -1;
  }

  /**
   * 计算文件行
   */
  private int countRegionLines(byte[] region) {
    int lineNo = 0;
    for (int i = 0; i < region.length; i++) {
      if (region[i] == "\n".getBytes()[0]) {
        lineNo++;
      }
    }
    return lineNo;
  }

  /**
   * 累加结果
   */
  private void count(Map<String, LongAdder> ageToCntMap, String data) {
    if (data == null || data.length() == 0) {
      return;
    }
    List<String> lineResult = Arrays.stream(data.split(" "))
        .filter(ele -> !"".equals(ele) && !"null".equals(ele))
        .collect(Collectors.toList());
    // age为空时 归类到unknown
    String age = lineResult.size() == 3 ? lineResult.get(2) : "unknown";
    LongAdder cnt = ageToCntMap.get(age);
    if (cnt == null) {
      cnt = new LongAdder();
      cnt.increment();
      ageToCntMap.put(age, cnt);
    } else {
      cnt.increment();
    }
  }


  private void ageGroup(String parentPath, String sourceFileName, String destFileName) throws IOException, InterruptedException, ExecutionException {
    readFile(parentPath + sourceFileName);
    // 等待所有任务执行完成后再执行结果输出
    countDownLatch.await();
    futureList.forEach(future -> {
      try {
        Map<String, LongAdder> map = future.get();
        map.forEach((age, cnt) -> {
          LongAdder result = this.ageToCntMap.getOrDefault(age, new LongAdder());
          result.add(cnt.longValue());
          this.ageToCntMap.put(age, result);
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    });
    executor.shutdown();
    writeFile(parentPath + destFileName);
  }

  /**
   * 把归并后的总结果集合 写入result.txt
   */
  private void writeFile(String filePath) throws IOException {
    File file = new File(filePath);
    File parent = file.getParentFile();
    if (!parent.exists()) {
      parent.mkdirs();
      file.createNewFile();
    }
    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      outputStream.write("age\tcnt\n".getBytes(StandardCharsets.UTF_8));
      this.ageToCntMap.forEach((age, cnt) -> {
        // 表头不输出
        if ("age".equals(age)) {
          return;
        }
        String content = age + "\t" + cnt + "\n";
        try {
          outputStream.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
          logger.log(Level.WARNING, "write key error,key:" + age + ",cnt:" + cnt, e);
        }
      });
    } catch (Exception e) {
      logger.log(Level.WARNING, "write file error", e);
    }
  }

  public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
    String parentPath = "/Users/yqg/code/sylvanas/leetcode/src/main/java/com/sylvanas/";
    // 文件的绝对路径
    new AgeGroup().ageGroup(parentPath, "info.txt", "result.txt");
  }

}


class NamedThreadFactory implements ThreadFactory {
  private final ThreadGroup group;
  private final AtomicInteger threadNumber;
  private final String namePrefix;


  public NamedThreadFactory(String namePrefix) {
    this.threadNumber = new AtomicInteger(1);
    SecurityManager s = System.getSecurityManager();
    this.group = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    this.namePrefix = namePrefix;
  }

  public Thread newThread(Runnable task) {
    return new Thread(this.group, task, this.namePrefix + "-thread-" + this.threadNumber.getAndIncrement(), 0L);
  }

}
