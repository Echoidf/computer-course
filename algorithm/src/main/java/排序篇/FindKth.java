package 排序篇;

/**
 * @author 左齐亮
 * @version 1.0
 * 用快排思想在O(n)内查找第k大元素
 */
public class FindKth {

    public static int findKthBiggest(int[] a, int k) {
        if (a == null || a.length < k || k <= 0) return -1;
        int partition = partition(a, 0, a.length - 1);
        while (partition + 1 != k) {
            if (partition + 1 < k) {
                partition = partition(a, partition + 1, a.length - 1);
            } else {
                partition = partition(a, 0, partition - 1);
            }
        }
        return a[partition];
    }

    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (a[j] >= pivot) {
                swap(a, i, j);
                i++;
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
        int[] arr = new int[]{4, 2, 5, 12, 3,3};
        int kthBiggest = findKthBiggest(arr, 6);
        System.out.println(kthBiggest);
    }
}
