package com.sylvanas.sort.algorithm;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 7, 8, 5, 4};
        sort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}
