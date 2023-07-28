package com.sylvanas;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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


  private void readFile(String filePath) throws FileNotFoundException, InterruptedException {
    logger.info("starting to group file data...");
    File file = new File(filePath);
    long fileLength = file.length();
    int regionSize = 1024 * 1024;
    int batchNum = (int) Math.ceil(fileLength * 1.0 / regionSize);
    countDownLatch = new CountDownLatch(batchNum);
    int regionIndex = 0;
    while (regionIndex < batchNum) {
      regionRead(filePath, regionIndex++, regionSize);
    }
  }


  private void regionRead(String filePath, int regionIndex, int regionSize) {
    executor.submit(() -> {
      try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
        byte[] region = new byte[regionSize];
        int posOffset = regionIndex * regionSize;
        int actualSize = raf.read(region, posOffset, regionSize);
        // 没有内容
        if (actualSize < 0) {
          logger.info("region is empty!");
          return;
        }
        // 清理多余空间
        if (actualSize < regionSize) {
          byte[] actualRegion = new byte[actualSize];
          System.arraycopy(region, 0, actualRegion, 0, actualSize);
          region = actualRegion;
        }
        // 处理文件块
        extractRegionData(filePath, region, posOffset);
      } catch (Exception e) {
        logger.log(Level.WARNING, "read file error,region index is:" + regionIndex, e);
      } finally {
        // 不管任务是否真的执行完 都默认文件块被处理
        countDownLatch.countDown();
        logger.info("task finished!");
      }
    });
  }

  private void extractRegionData(String filePath, byte[] region, int posOffset) {
    boolean isFinalRead = false;
    try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
      int start = 0;
      while (true) {
        //
        int newLineIndex = indexOfLineSeparator(region, start);
        // 没有找到换行符说明可能只有一行 也可能当前是最后一行的前一行 读取后计数并推出
        if (newLineIndex < 0) {
          isFinalRead = true;
        }
        // 偏转指针到找到的行尾
        raf.seek(posOffset + newLineIndex + 1);
        String data = raf.readLine();
        data = new String(data.getBytes(StandardCharsets.ISO_8859_1));
        count(data);
        start = newLineIndex + 1;
        if (isFinalRead) {
          break;
        }
      }
    } catch (Exception e) {
      logger.log(Level.WARNING, "extract region error", e);
    }
  }

  /**
   * 查找一个region从指定位置之后的换行符位置
   */
  private int indexOfLineSeparator(byte[] region, int start) {
    for (int i = start; i < region.length; i++) {
      if (region[i] == "\n".getBytes()[0]) {
        return i;
      }
    }
    return -1;
  }

  private void count(String data) {
    if (data == null || data.length() == 0) {
      return;
    }
    List<String> lineResult = Arrays.stream(data.split(" "))
        .filter(ele -> !"".equals(ele) && !"null".equals(ele))
        .collect(Collectors.toList());

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


  private void ageGroup(String parentPath, String sourceFileName, String destFileName) throws FileNotFoundException, InterruptedException {
    readFile(parentPath + sourceFileName);
    // 等待所有任务执行完成后再执行结果输出
    countDownLatch.await();
    writeFile(parentPath + destFileName);
  }

  /**
   * 把归并后的总结果集合 写入result.txt
   */
  private void writeFile(String filePath) {

  }

  public static void main(String[] args) throws IOException, InterruptedException {
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
