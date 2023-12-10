package com.sylvanas.sort.algorithm;

import java.util.Arrays;

public class HeapSort {

    public static void sort(int[] arr) {
        buildMaxTopHeap(arr);
        int size = arr.length;
        while (size > 1) {
            swap(arr, 0, size - 1);
            size--;
            heapify(arr, 0, size);
        }

    }

    private static void heapify(int[] arr, int index, int size) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        while (left < size) {
            int largestIndex;
            if (right < size && arr[left] < arr[right]) {
                largestIndex = right;
            } else {
                largestIndex = left;
            }

            if (arr[index] > arr[largestIndex]) {
                largestIndex = index;
            }

            if (largestIndex == index) {
                break;
            }

            swap(arr, index, largestIndex);

            index = largestIndex;
            left = 2 * index + 1;
            right = 2 * index + 2;
        }
    }

    private static void buildMaxTopHeap(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int currentIndex = i;
            int fatherIndex = (currentIndex - 1) / 2;
            while (arr[currentIndex] > arr[fatherIndex]) {
                swap(arr, currentIndex, fatherIndex);
                currentIndex = fatherIndex;
                fatherIndex = (currentIndex - 1) / 2;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 7, 8, 5, 4};
        exerciseSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }


    public static void exerciseSort(int[] arr) {
        exerciseBuildMaxTopHeap(arr);
        int size = arr.length;
        while (size > 0) {
            swap(arr, 0, size - 1);
            size--;
            exerciseHeapify(arr, 0, size);
        }
    }


    public static void exerciseBuildMaxTopHeap(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int curIndex = i;
            int fatherIndex = (i - 1) / 2;
            while (arr[curIndex] > arr[fatherIndex]) {
                swap(arr, curIndex, fatherIndex);
                curIndex = fatherIndex;
                fatherIndex = (curIndex - 1) / 2;
            }
        }
    }

    private static void exerciseHeapify(int[] arr, int curIndex, int size) {
        int leftIndex = 2 * curIndex + 1;
        int rightIndex = 2 * curIndex + 2;
        while (rightIndex < size) {
            int largestIndex;
            if (rightIndex < size && arr[rightIndex] > arr[leftIndex]) {
                largestIndex = rightIndex;
            } else {
                largestIndex = leftIndex;
            }
            if (arr[largestIndex] < arr[curIndex]) {
                largestIndex = curIndex;
            }
            if (largestIndex == curIndex) {
                break;
            }
            swap(arr, curIndex, largestIndex);
            curIndex = largestIndex;

            leftIndex = 2 * curIndex + 1;
            rightIndex = 2 * curIndex + 2;
        }

    }

}
