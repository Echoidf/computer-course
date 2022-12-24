package 排序篇;

import java.util.Arrays;

/**
 * @author 左齐亮
 * @version 1.0
 */
public class MergeSort {
    /**
     * 归并排序
     *
     * @param a
     */
    public static void mergeSort(int[] a) {
        merge_sort_c(a, 0, a.length - 1);
    }

    private static void merge_sort_c(int[] a, int p, int r) {
        if (p >= r) return;
        // 取中间位置q
        int q = (p + r) / 2;
        // 递归调用
        merge_sort_c(a, p, q);
        merge_sort_c(a, q + 1, r);
        // 合并数组
        //merge(a, p, q, r);
        mergeBySentry(a, p, q, r);
    }

    // 将两个数组合并a[p~q] a[q+1~r]
    private static void merge(int[] a, int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int k = 0;
        int[] tmp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }
        // 判断哪个子数组中还有剩余数据
        while (j <= r) {
            tmp[k++] = a[j++];
        }
        while (i <= q) {
            tmp[k++] = a[i++];
        }
        //将tmp中的数组拷贝回a[p...r]
        if (r - p + 1 >= 0) System.arraycopy(tmp, 0, a, p, r - p + 1);
    }

    // 合并数组--哨兵
    private static void mergeBySentry(int[] a, int p, int q, int r) {
        int[] leftArr = new int[q - p + 2];
        int[] rightArr = new int[r - q + 1];
        System.arraycopy(a, p, leftArr, 0, q - p + 1);
        //第一个数组添加哨兵
        leftArr[q - p + 1] = Integer.MAX_VALUE;
        System.arraycopy(a, q + 1, rightArr, 0, r - q);
        //第二个数组添加哨兵
        rightArr[r - q] = Integer.MAX_VALUE;

        int i = 0, j = 0;
        int k = p;
        while (k <= r) {
            // 当左边数组到达哨兵值时，i不再增加，直到右边数组读取完剩余值，同理右边数组也一样
            if (leftArr[i] <= rightArr[j]) {
                a[k++] = leftArr[i++];
            } else {
                a[k++] = rightArr[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 1, 2, 4, 7, 10, 9, 6};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
