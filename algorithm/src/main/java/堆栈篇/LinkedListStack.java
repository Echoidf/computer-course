package 堆栈篇;


/**
 * @author 左齐亮
 * @version 1.0
 * 基于链表实现栈
 */
public class LinkedListStack<T> {
    private Node top = null;

    // 入栈
    public void push(int value) {
        Node node = new Node(value);
        // 判断是否栈空
        if (top != null) {
            node.next = top;
        }
        top = node;
    }

    /**
     * 出栈
     * -1表示栈中没有数据
     * @return
     */
    public int pop() {
        // 判断是否栈空
        if(top == null) return -1;
        int val = top.val;
        top = top.next;
        return val;
    }

    public void printAll() {
        Node p = top;
        while (p!=null){
            System.out.print(p.val + ',');
            p = p.next;
        }
        System.out.println();
    }
    private static class Node {
        private int val;
        private Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

}
