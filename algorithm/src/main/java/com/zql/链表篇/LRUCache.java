package com.zql.链表篇;

import java.util.Scanner;

/**
 * @author 左齐亮
 * @version 1.0
 */
public class LRUCache<T> {
    /**
     * 默认链表容量
     */
    private final static Integer DEFAULT_CAPACITY = 10;

    /**
     * 头结点
     */
    private SNode<T> headNode;

    /**
     * 链表长度
     */
    private Integer length;

    /**
     * 链表容量
     */
    private Integer capacity;

    public LRUCache() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUCache(Integer capacity) {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void add(T data) {
        SNode preNode = findPreNode(data);
        // 链表中存在，删除原数据，再插入到链表的头部
        if (preNode != null) {
            deleteElemOptim(preNode);
            intsertElemAtBegin(data);
        }
        // 链表中不存在
        else {
            // 缓存已满，删除尾结点
            if(length >= this.capacity){
                deleteElemAtEnd();
            }
            // 插入链表头部
            intsertElemAtBegin(data);
        }
    }

    /**
     * 删除preNode结点下一个元素
     *
     * @param preNode
     */
    private void deleteElemOptim(SNode preNode) {
        SNode next = preNode.getNext();
        preNode.setNext(next.next);
        next = null;
        length--;
    }

    /**
     * 链表头部插入节点
     *
     * @param data
     */
    private void intsertElemAtBegin(T data) {
        SNode next = headNode.getNext();
        headNode.setNext(new SNode(data, next));
        length++;
    }

    /**
     * 获取查找到元素的前一个结点
     *
     * @param data
     * @return
     */
    private SNode findPreNode(T data) {
        SNode sNode = headNode;
        while (sNode.getNext() != null) {
            if (sNode.getNext().getElement().equals(data)) {
                return sNode;
            }
            sNode = sNode.getNext();
        }
        return null;
    }

    /**
     * 删除尾结点
     */
    private void deleteElemAtEnd() {
        SNode ptr = headNode;
        if (ptr.getNext() == null) return;
        //倒数第二个结点
        while (ptr.getNext().getNext() != null) {
            ptr = ptr.getNext();
        }
        SNode temp = ptr.getNext();
        ptr.setNext(null);
        temp = null;
        length--;
    }

    private void printAll() {
        SNode ptr = headNode.getNext();
        while (ptr != null) {
            System.out.print(ptr.getElement() + ",");
            ptr = ptr.getNext();
        }
        System.out.println();
    }

    public class SNode<T> {
        private T element;
        private SNode next;

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public SNode(T element) {
            this.element = element;
        }

        public SNode() {
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUCache list = new LRUCache();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            list.add(scanner.nextInt());
            list.printAll();
        }
    }
}
