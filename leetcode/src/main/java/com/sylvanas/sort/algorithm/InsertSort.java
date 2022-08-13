package com.sylvanas.sort.algorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class InsertSort {

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int prefix = i;
            int newEle = arr[prefix + 1];
            while (prefix >= 0 && arr[prefix] > newEle) {
                arr[prefix + 1] = arr[prefix];
                prefix--;
            }
            arr[prefix + 1] = newEle;
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 7, 8, 5, 4};
        sort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}
