package com.sylvanas.others;


import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

public class BloomFilterUtil {

  private static BloomFilterUtil instance;

  private BloomFilterUtil() {
  }


  public static BloomFilterUtil getInstance() {
    if (instance != null) {
      return instance;
    }
    synchronized (BloomFilterUtil.class) {
      if (instance != null) {
        return instance;
      }
      instance = new BloomFilterUtil();
    }
    return instance;
  }

  /**
   * 预估数据量
   */
  private static final int INSERTIONS = 100000;

  /**
   * 判重错误率
   */
  private static final double FPP = 0.0001;

  private BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), INSERTIONS, FPP);

  public void addElement(String value) {
    bloomFilter.put(value);
  }

  public boolean containsElement(String value) {
    return bloomFilter.mightContain(value);
  }

  public static void main(String[] args) {
    BloomFilterUtil bloomFilterUtil = BloomFilterUtil.getInstance();
    bloomFilterUtil.addElement("lph");
    System.out.println(bloomFilterUtil.containsElement("lph"));

  }
}
