package com.sylvanas.something;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IPLongUtils {


    /**
     * IP转long
     * <p>
     * ip 由4字节（byte）在2进制里面是一个32bit二进制数
     * 1. 每一个字节是8位 4字节就是32位
     * 2. 每一位都是无符号 0~255
     *
     * @param ip IPV4 地址
     * @return long值
     */
    public static long ip2Long(String ip) {
        String[] ipValues = ip.split("\\.");
        return (Long.parseLong(ipValues[0]) << 24) +
                (Long.parseLong(ipValues[1]) << 16) +
                (Long.parseLong(ipValues[2]) << 8) +
                (Long.parseLong(ipValues[3]));
    }

    /**
     * long转ip
     *
     * @param ip long型ip
     * @return 字符型ip
     */
    public static String long2Ip(long ip) {
        return new StringBuilder()
                .append(ip >>> 24).append(".")
                .append((ip >>> 16) & 0xFF).append(".")
                .append((ip >>> 8) & 0xFF).append(".")
                .append(ip & 0xFF).append(".")
                .toString();
    }


    public static void main(String[] args) {
        String ip = "192.168.0.1";
        System.out.println(ip2Long(ip));
        System.out.println(long2Ip(ip2Long(ip)));
    }


}
