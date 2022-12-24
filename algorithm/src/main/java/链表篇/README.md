### 如何基于链表实现 LRU 缓存淘汰算法？

思路是这样的：我们维护一个有序单链表，越靠近链表尾部的结点是越早之前访问的。当有一个新的数据被访问时，我们从链表头开始顺序遍历链表。

- 如果此数据之前已经被缓存在链表中了，我们遍历得到这个数据对应的结点，并将其从原来的位置删除，然后再插入到链表的头部。
- 如果此数据没有在缓存链表中，又可以分为两种情况：

1. 如果此时缓存未满，则将此结点直接插入到链表的头部；
2. 如果此时缓存已满，则链表尾结点删除，将新的数据结点插入链表的头部。

思路优化： 引入散列表 HashMap 记录每个数据的位置，将缓存访问的时间复杂度降到 O(1)

### LeetCode刷题推荐：

1. [反转链表](https://leetcode.cn/problems/reverse-linked-list/) <br/>

```java
    // 1. 递归写法
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}

//2.迭代写法
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode node = cur.next;
            cur.next = pre;
            pre = cur;
            cur = node;
        }
        return pre;
    }
} 
   ```

2. [反转链表II](https://leetcode.cn/problems/reverse-linked-list-ii/)

   给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回反转后的链表

```java
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) return head;
        ListNode pre = null;
        ListNode cur = head;
        for (int i = 1; i < left; i++) {
            pre = cur;
            cur = cur.next;
        }
        ListNode temp = cur;
        ListNode succ = temp.next;
        for (int i = left; i < right; i++) {
            temp = temp.next;
            succ = temp.next;
        }
        // 这里要注意的是如果left前一个结点和right后一个结点都为空说明要反转整个链表
        if (pre == null && succ == null) return reverseList(cur);
        // right后一个结点不为空才需要切断
        if (succ != null) temp.next = null; //切断
        ListNode root = reverseList(cur);
        // pre不为空就将其指向反转链表的头部
        if (pre != null) pre.next = root;
        else head = root;
        ListNode temp2 = root;
        while (temp2.next != null) {
            temp2 = temp2.next;
        }
        // 反转链表的尾部指向right后一个结点
        temp2.next = succ;
        return head;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode node = cur.next;
            cur.next = pre;
            pre = cur;
            cur = node;
        }
        return pre;
    }
}
```

3. [合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/)

   将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

```java
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }

        p.next = (list1 == null ? list2 : list1);
        return dummy.next;
    }
}
```
