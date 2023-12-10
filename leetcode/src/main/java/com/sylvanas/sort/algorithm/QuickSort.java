package com.sylvanas.sort.algorithm;

import java.util.Arrays;

public class QuickSort {

    public static void sort(int[] arr) {
        quickSort2(arr, 0, arr.length - 1);
    }


    public static void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int base = arr[left];
        int baseIndex = left;
        for (int i = baseIndex + 1; i <= right; i++) {
            if (arr[i] < base) {
                swap(arr, i, ++baseIndex);
            }
        }
        swap(arr, left, baseIndex);
        quickSort(arr, left, baseIndex - 1);
        quickSort(arr, baseIndex + 1, right);
    }


    public static void quickSort2(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int base = arr[left];
        while (l != r) {
            while (l < r && arr[r] <= base) {
                r--;
            }
            while (l < r && arr[l] >= base) {
                l++;
            }
            if (l < r) {
                swap(arr, l, r);
            }
        }
        swap(arr, left, l);
        quickSort2(arr, left, l - 1);
        quickSort2(arr, l + 1, right);
    }

    public static void exercise(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int base = arr[left];
        int baseIndex = left;
        for (int i = baseIndex + 1; i <= right; i++) {
            if (arr[i] < base) {
                swap(arr, i, ++baseIndex);
            }
        }
        swap(arr, left, baseIndex);
        exercise(arr, left, baseIndex - 1);
        exercise(arr, baseIndex + 1, right);
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 7, 6, 10, 8, 5, 4};
        exercise(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(System.out::println);
    }

}
