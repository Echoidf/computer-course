package com.zql.排序篇;

import java.util.Arrays;

/**
 * @author 左齐亮
 * @version 1.0
 */
public class Sort {
    /**
     * 冒泡排序
     *
     * @param a 待排序的数组
     */
    public static void bubbleSort(int[] a) {
        if (a.length <= 1) return;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     *
     * @param a 待排序的数组
     */
    public static void insertSort(int[] a) {
        if (a.length <= 1) return;
        for (int i = 1; i < a.length; i++) {
            int value = a[i];
            // 为a[i]寻找插入位置
            int j = i - 1;
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j]; // 数据移动
                } else break;
            }
            // 插入数据
            a[j + 1] = value;
        }
    }

    /**
     * 选择排序
     *
     * @param a 待排序的数组
     */
    public static void selectSort(int[] a) {
        if (a.length <= 1) return;
        for (int i = 0; i < a.length; i++) {
            int min = a[i];
            int pos = i;
            for (int j = i + 1; j < a.length; ++j) {
                if (a[j] < min) {
                    min = a[j];
                    pos = j;
                }
            }
            if (pos != i) {
                // 数据交换
                int temp = a[i];
                a[i] = min;
                a[pos] = temp;
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 1, 2, 4, 7, 10, 9, 6};
        //bubbleSort(arr);
        //insertSort(arr);
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
