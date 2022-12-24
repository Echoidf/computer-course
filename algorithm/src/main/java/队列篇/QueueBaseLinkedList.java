package 队列篇;


/**
 * @author 左齐亮
 * @version 1.0
 * 基于链表实现链式队列
 */
public class QueueBaseLinkedList {
    private Node head;
    private Node tail;

    public QueueBaseLinkedList() {
        head = new Node();
        tail = new Node();
        head.next = tail;
    }

    /**
     * 入队
     */
    public void enqueue(String val){
       if(tail == null){
           Node node = new Node(val, null);
           head = node;
           tail = node;
       }else{
           tail.next = new Node(val,null);
           tail = tail.next;
       }
    }

    /**
     * 出队
     */
    public String dequeue(){
        if(head == null) return null;
        String data = head.val;
        head = head.next;
        if(head == null){
            tail = null;
        }
        return data;
    }

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }

    private static class Node {
        private String val;
        private Node next;

        public Node(String val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(String val) {
            this.val = val;
        }

        public Node() {
        }

        public String getVal() {
            return val;
        }
    }
}
