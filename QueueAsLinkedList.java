
// Online IDE - Code Editor, Compiler, Interpreter

public class QueueAsLinkedList
{
    private Node front, rear;
    private int size;
    
    private class Node {
        int value;
        Node next;
    }
    
    public QueueAsLinkedList() {
        front = null;
        rear = null;
        size = 0;
    }
    
    public boolean isEmpty() {
        return (size == 0);
    }
    
    public void enqueue(int value) {
        Node oldRear = rear;
        rear = new Node();
        rear.value = value;
        rear.next = null;
        if (isEmpty()) {
            front = rear;
        }
        else {
            oldRear.next = rear;
        }
        size++;
        System.out.println(value + " added to the queue");
    }
    
    public int dequeue() {
        int frontValue = front.value;
        front = front.next;
        if (isEmpty()) {
            rear = null;
        }
        size--;
        System.out.println(frontValue + " removed from the queue");
        return frontValue;
        
    }
    
    
    //How you run an app
    public static void main(String args[]) {
        QueueAsLinkedList queue = new QueueAsLinkedList();
         queue.enqueue(6);
         queue.dequeue();
         queue.enqueue(3);
         queue.enqueue(99);
         queue.enqueue(56);
         queue.dequeue();
         queue.enqueue(43);
         queue.dequeue();
         queue.enqueue(89);
         queue.enqueue(77);
         queue.dequeue();
         queue.enqueue(32);
         queue.enqueue(232);
        printList(queue.front);
    }
    
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.format("%d ", temp.value);
            temp = temp.next;
        }
        System.out.println();
    }
}
