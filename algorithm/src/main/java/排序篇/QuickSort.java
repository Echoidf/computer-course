package 排序篇;

import java.util.Arrays;

/**
 * @author 左齐亮
 * @version 1.0
 */
public class QuickSort {

    public static void quickSort(int[] a) {
        quickSortInternally(a, 0, a.length - 1);
    }

    private static void quickSortInternally(int[] a, int p, int r) {
        if (p >= r) return;
        int q = partition(a, p, r);
        quickSortInternally(a, p, q - 1);
        quickSortInternally(a, q + 1, r);
    }

    /**
     * 获取分区点
     */
    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (a[j] < pivot) {
                if(i==j) i++;
                else {
                    swap(a,i,j);
                    i++;
                }
            }
        }
        swap(a, i, r);

        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 1, 2, 4, 7, 10, 9, 6};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
