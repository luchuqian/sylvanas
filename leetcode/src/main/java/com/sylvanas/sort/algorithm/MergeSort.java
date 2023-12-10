package com.sylvanas.sort.algorithm;

import java.util.Arrays;

public class MergeSort {


    public static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = left + (right - left) / 2;
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);
        merge(arr, left, middle, right);
    }

    private static void merge(int[] arr, int left, int middle, int right) {
        int leftLength = middle - left + 1;
        int rightLength = right - middle;

        int[] leftArr = new int[leftLength];
        int[] rightArr = new int[rightLength];

        for (int i = 0; i < leftLength; i++) {
            leftArr[i] = arr[left + i];
        }

        for (int j = 0; j < rightLength; j++) {
            rightArr[j] = arr[middle + 1 + j];
        }
        int k = left;
        int i = 0;
        int j = 0;
        while (i < leftLength && j < rightLength) {
            if (leftArr[i] < rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < leftLength) {
            arr[k++] = leftArr[i++];
        }
        while (j < rightLength) {
            arr[k++] = rightArr[j++];
        }
    }


    public void exerciseSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int middle = left + (right - left) / 2;
        exerciseSort(arr, left, middle);
        exerciseSort(arr, middle + 1, right);
        exerciseMerge(arr, left, middle, right);
    }

    public void exerciseMerge(int[] arr, int left, int middle, int right) {

    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 7, 8, 5, 4};
        sort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

}
