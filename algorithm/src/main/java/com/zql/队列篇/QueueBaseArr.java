package com.zql.队列篇;

/**
 * @author 左齐亮
 * @version 1.0
 * 基于数组实现顺序队列
 */
public class QueueBaseArr {
    // 数组：items，数组大小：n
    private String[] items;
    private int n = 0;
    // head表示队头下标，tail表示队尾下标
    private int head = 0;
    private int tail = 0;

    public QueueBaseArr(int n) {
        this.n = n;
        items = new String[n];
    }

    /**
     * 队尾入队
     *
     * @param item
     */
    public boolean enqueue(String item) {
        if (tail == n) {
            // 队列已满
            if (head == 0) return false;
            // 数据搬移
            for (int i = head; i < tail; ++i) {
                items[i - head] = items[head];
            }
            // 更新head和tail
            tail = tail - head;
            head = 0;
        }
        items[tail] = item;
        ++tail;
        return true;
    }

    /**
     * 队头出队
     *
     * @return
     */
    public String dequeue() {
        // 队列为空
        if (head == tail) {
            return null;
        }
        String item = items[head];
        ++head;
        return item;
    }
}
