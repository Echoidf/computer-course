## 排序篇

### 1.如何分析排序算法

- 排序算法的执行效率——时间复杂度、数据规模、比较/交换/移动次数
- 排序算法的内存消耗——空间复杂度（原地排序是指空间复杂度为O(1))
- 排序算法的稳定性——如果待排序的序列中存在值相等的元素，经过排序之后，相等元素之间原有的先后顺序不变

### 2.冒泡排序

冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，看是否满足大小关系要求。如果不满足就让它俩互换。一次冒泡会让至少一个元素移动到它应该在的位置，重复 n 次，就完成了 n 个数据的排序工作

```java
 public static void bubbleSort(int[] a) {
        if (a.length <= 1) return;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if(a[i] >= a[j]){
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
```
分析：冒泡排序空间复杂度为O(1),是原地排序，前后元素相等时可以不进行交换，是稳定的排序算法。
最好情况的时间复杂度为O(n), 最坏为O(n2)

### 3.插入排序
将数组中的数据分为两个区间，**已排序区间**和**未排序区间**。初始已排序区间只有一个元素，就是数组的第一个元素。插入算法的核心思想是取未排序区间中的元素，在已排序区间中找到合适的插入位置将其插入，并保证已排序区间数据一直有序。重复这个过程，直到未排序区间中元素为空，算法结束

```java
public static void insertSort(int[] a) {
        if (a.length <= 1) return;
        for (int i = 1; i < a.length; i++) {
            int value = a[i];
            // 为a[i]寻找插入位置
            int j = i-1;
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j]; // 数据移动
                }else break;
            }
            // 插入数据
            a[j+1] = value;
        }
    }
```
分析：从第二个元素开始，如果前面的元素比后面的大，就将前面已排好序的一个个往后移，直到遇到小于等于所选元素，再将所选元素插入到移动出来的空位

插入排序空间复杂度为O(1),是原地排序，是稳定排序算法，最好情况的时间复杂度为O(n), 最坏为O(n2)，平均时间复杂度为O(n2)

### 4.选择排序
选择排序算法的实现思路有点类似插入排序，也分已排序区间和未排序区间。但是选择排序每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾
```java
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
```
分析：选择排序空间复杂度为 O(1)，是一种原地排序算法。选择排序的最好情况时间复杂度、最坏情况和平均情况时间复杂度都为 O(n2)

选择排序每次都要找剩余未排序元素中的最小值，并和前面的元素交换位置，这样破坏了稳定性。是不稳定的算法。

## 归并排序和快速排序——适合大规模数据排序（分治思想）
### 1.归并排序
归并排序使用的是分治思想, 详情见代码MergeSort.java
```java
public class MergeSort {
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
}
```
分析：归并排序是一个稳定的排序算法,时间复杂度是 O(nlogn),空间复杂度是 O(n)

### 2.快速排序
思路：如果要排序数组中下标从 p 到 r 之间的一组数据，我们选择 p 到 r 之间的任意一个数据作为 pivot（分区点），
遍历 p 到 r 之间的数据，将小于 pivot 的放到左边，将大于 pivot 的放到右边，将 pivot 放到中间。经过这一步骤之后，数组 p 到 r 之间的数据就被分成了三个部分，前面 p 到 q-1 之间都是小于 pivot 的，中间是 pivot，后面的 q+1 到 r 之间是大于 pivot 的
可以用递归排序下标从 p 到 q-1 之间的数据和下标从 q+1 到 r 之间的数据，直到区间缩小为 1，就说明所有的数据都有序了

```java
public class QuickSort {

    public static void quickSort(int[] a) {
        quickSortInternally(a, 0, a.length - 1);
    }

    private static void quickSortInternally(int[] a, int p, int r) {
        if(p >= r) return;
        int q = partition(a, p, r);
        quickSortInternally(a,p,q-1);
        quickSortInternally(a,q+1,r);
    }

    /**
     * 获取分区点
     */
    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if(a[j] < pivot) {
                if(i == j){
                    ++i;
                }else {
                    int tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                }
            }
        }
        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;

        return i;
    }
}
```
分析：归并排序的处理过程是由下到上的，先处理子问题，然后再合并。而快排正好相反，它的处理过程是由上到下的，先分区，然后再处理子问题。
快速排序是原地排序，可以解决归并排序占用太多内存的问题。
快排的时间复杂度也是 O(nlogn)，最坏情况下的时间复杂度是 O(n2)，但是退化到最坏情况的概率很小。

### 如何用快排思想在O(n)内查找第k大元素？
此时排序应从大到小进行排序，仍然对数组进行分区，将数组分成三部分：a[0,partition-1],a[partition],a[partition+1,n-1]。
- 此时若partition+1 == k,则说明第k大元素就是a[partition]；
- 若partition+1 < k,继续对数组a[partition+1,n-1]进行分区；
- 若partition+1 > k,继续对数组a[0,partition-1]进行分区；
```java
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
}
```

### 思考：
现在你有 10 个接口访问日志文件，每个日志文件大小约 300MB，每个文件里的日志都是按照时间戳从小到大排序的。你希望将这 10 个较小的日志文件，合并为 1 个日志文件，合并之后的日志仍然按照时间戳从小到大排列。如果处理上述排序任务的机器内存只有 1GB，你有什么好的解决思路，能“快速”地将这 10 个日志文件合并吗？

1. 申请10个40M的数组和一个400M的数组。
2. 每个文件都读40M，取各数组中最大时间戳中的最小值。
3. 然后利用二分查找，在其他数组中快速定位到小于/等于该时间戳的位置，并做标记。
4. 再把各数组中标记位置之前的数据全部放在申请的400M内存中，
5. 在原来的40M数组中清除已参加排序的数据。[可优化成不挪动数据，只是用两个索引标记有效数据的起始和截止位置]
6. 对400M内存中的有效数据[没装满]做快排。
将排好序的直接写文件。
7. 再把每个数组尽量填充满。从第2步开始继续，知道各个文件都读区完毕。

这么做的好处有：
1. 每个文件的内容只读区一次，且是批量读区。比每次只取一条快得多。
2. 充分利用了读区到内存中的数据。曹源 同学在文件中查找那个中间数是会比较困难的。
3. 每个拷贝到400M大数组中参加快排的数据都被写到了文件中，这样每个数只参加了一次快排。





