
// Online IDE - Code Editor, Compiler, Interpreter

public class ReverseLinkedList
{
    private Node head;
 
    private static class Node {
        private int value;
        private Node next;
 
        Node(int value) {
            this.value = value;
 
        }
    }
    
    public ReverseLinkedList() {
        head = null;
    }
    
    public boolean isEmpty() {
        return (head == null);
    }
    
    public void addToLL(Node node) {
 
        if (head == null) {
            head = node;
        } else {
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;
 
            temp.next = node;
        }
    }

    //Instead of A -> B -> C, we need A <- B <- C. 3 Nodes at once.
    public Node reverseTheList(Node currentNode) {
        Node prevNode = null;
        Node nextNode;
        
        //Assuming currentNode is A for first iteration
        while (currentNode != null) {
            //NextNode is now B 
            nextNode = currentNode.next;
            
            //A.next is now null; null <- A -> B -> C 
            currentNode.next = prevNode;
            
            //prevNode is now A instead of null 
            prevNode = currentNode;
            
            //currentNode is now B 
            currentNode = nextNode;
        }
        
        return prevNode;
    }
    public static void main(String args[]) {
        ReverseLinkedList list = new ReverseLinkedList();
        Node head = new Node(5);
        list.addToLL(head);
        list.addToLL(new Node(6));
        list.addToLL(new Node(7));
        list.addToLL(new Node(8));
        list.addToLL(new Node(9));
        printList(list.head);
        Node reversedHead = list.reverseTheList(head);
        printList(reversedHead);
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
