package com.sylvanas;


import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

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
    private Map<String, Integer> ageToCntMap = new ConcurrentHashMap<>();
    /**
     * 用来控制主线程什么时候可以结束
     * 默认1000w
     */
    private CountDownLatch countDownLatch = new CountDownLatch(10000000);
    /**
     * 一个约数 表示文件的实际可读行
     */
    private AtomicInteger actualReadableLines = new AtomicInteger(10000000);

    private ExecutorService executor = new ThreadPoolExecutor(10,
            10,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(2000),
            new NamedThreadFactory("age-group"),
            (task, executor) -> logger.info("task is rejected!")
    );

    /**
     * 整体处理流程: 多线程归并思想 把大数据量划分为小任务执行 传入共享变量 最后输出结果集合到文件
     * 1. 默认1000w行是一个明确的数量 划分1000w行的区间
     * a. 小于1000w行 会下发大量的空任务 每个任务需要自行防空 每一个计数任务如果检测到当前自己扫描处理的区间大于实际可读行数时 快速结束
     * b. 如果超过1000w行 需要补充下发任务 同时降级到主线程扫描后续文件行 分发任务到子线程
     * c. 刚好1000w行 理想情况
     * 2. 单个区间1w行数据 下发每个区间的统计任务 1w是根据已知数据格式和内存安全保守估计的 机器资源越大 单个任务可以处理越大的区间 同时也可以开辟越多工作线程
     * 3. 考虑不可控因素或者执行过程中的脏数据影响 每个区间的任务如果处理成功 都暂存一份至文件 同时会记录一份已处理的区间到另一个文件groupedRange.txt 重跑任务时可以快速获取已经执行过的结果 加速重跑的任务结果
     * a. groupedRegion.txt 行数不会太大 n/10000的的行数 主要记录已经处理过的区间 每次任务启动优先加载该文件到内存中
     * 4. 线程池保守估计初始化10个核心线程 0个非核心线程 2000个阻塞队列长度 下发的任务时区间值和随机文件句柄 同时只有10个核心线程再并发 10w数据在内存中分拣 防止疯狂的开辟线程导致内存溢出
     * 5. 所有线程共享一个线程安全的map {@link this#ageToCntMap} 每个子任务处理完成时同时写入内存和写入文件groupedRange.txt
     */
    private void readFile(String filePath) throws IOException {
        logger.info("starting to group file data...");
        while (countDownLatch.getCount() > 0) {
            executor.submit(() -> {
                RandomAccessFile raf = null;
                try {
                    raf = new RandomAccessFile(filePath, "r");
                } catch (FileNotFoundException e) {
                   logger.info("read file error,",);
                }
                // 文件行指针
                raf.seek(0);
            });
        }
    }


    /**
     * 1. 尽可能快
     * 2. 1000w数据 内存有压力 需分批
     * 2.1 分批减缓内存压力
     * 2.2 并行加快归类
     * 2.3 同一个文件
     * 3. hash表存结果 单次写道
     */
    private void ageGroup() {


    }

    /**
     * 把归并后的总结果集合 写入result.txt
     */
    private void writeFile() {

    }

    public static void main(String[] args) throws IOException {
        // 这里使用文件的绝对路径
        new AgeGroup().readFile("D:\\code-from-github\\leetcode\\src\\main\\java\\com\\sylvanas\\info.txt");
    }

}

class User {
    private String id;
    private String name;
    private Integer age;


    public User(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
