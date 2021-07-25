
import java.util.ArrayList;
import java.util.Objects;

//Using ArrayList and LinkedList, create a chained ht.
public class HashMap<K, V> {

    //Private class for the nodes of the linked lists
    class HashNode<K, V> {
        K key;
        V value;
        final int hashCode;
        
        // Reference to next node
        HashNode<K, V> next;
        
        // Constructor
        public HashNode(K key, V value, int hashCode) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }
    }
    
    private ArrayList<HashNode<K, V>> bucketArray;
    private int numBuckets;
    private int size;
    
    public HashMap() {
        bucketArray = new ArrayList<>();
        numBuckets = 10;
        size = 0;
        
        for (int i = 0; i < numBuckets; i++) {
            bucketArray.add(null);
        }
    }
    //
    public int size() { 
        return size; 
        
    }
     
    //
    public boolean isEmpty() {
        return size == 0; 
    }
    
    // 
    private final int hashCode (K key) {
        return Objects.hashCode(key);
    }
    
    // This implements hash function to find index
    // for a key
    private int getBucketIndex(K key)
    {
        int hashCode = hashCode(key);
        int index = hashCode % numBuckets;
        // key.hashCode() coule be negative.
        index = index < 0 ? index * -1 : index;
        return index;
    }
    
    // Method to remove a given key
    public V remove(K key) {
        if (this.isEmpty()) return null;
        
        int buckIndex = this.getBucketIndex(key);
        int hashCode = this.hashCode(key);
        
        HashNode<K, V> head = bucketArray.get(buckIndex);
        
        HashNode<K, V> prevNode = null;
        
        //Keep going until "head" is the node we want to remove.
        while (head != null) {
            
            //If head has the right key and hashcode, stop here
            if (head.key.equals(key) && hashCode == head.hashCode) {
                break;
            }
            
            //Prev -> Head -> Head.next1
            
            //Prev->Head.next
            prevNode = head;
            
            //Prev->Head->Head.next2
            head = head.next;
        }
        
        // If key was not there
        if (head == null) return null;
        
        // Reduce size
        size--;
        
        if (prevNode != null) {
            prevNode.next = head.next;
        }
        else {
            bucketArray.set(buckIndex, head.next);
        }
        
        return head.value;
        
    }
    
    // Returns value for a key
    public V get(K key) {
        if (this.isEmpty()) return null;
        
        int buckIndex = this.getBucketIndex(key);
        int hashCode = this.hashCode(key);
        
        HashNode<K, V> head = bucketArray.get(buckIndex);
        
        //Keep going until "head" is the node we want to return.
        while (head != null) {
            
            //If head has the right key and hashcode, stop here
            if (head.key.equals(key) && hashCode == head.hashCode) {
                return head.value;
            }
            
            head = head.next;
        }
        
        return null;
        
    }
    
    // Adds a key value pair to hash
    public void add(K key, V value) {
        int buckIndex = this.getBucketIndex(key);
        int hashCode = this.hashCode(key);
        
        HashNode<K, V> head = bucketArray.get(buckIndex);
        
        //Check if we already have this key.
        while (head != null) {
            
            //If they key already exists
            if (head.key.equals(key) && hashCode == head.hashCode) {
                head.value = value;
                return;
            }
            
            //if not, march on
            head = head.next;
        }
        
        //Now we can add the key to the chain
        size++;
        
        //Where are we adding the new node?
        head = bucketArray.get(buckIndex);
        
        //Create the new node to be chained at the correct index
        HashNode<K, V> newNode = new HashNode<K, V>(key, value, hashCode);
        
        //We set the new node to be the first node (head) at this bucket index
        newNode.next = head;
        
        //Now we make it official at the bucket index.
        bucketArray.set(buckIndex, newNode);
        
        // If load factor goes beyond threshold, then
        // double hash table size
        if ((1.0 * size) / numBuckets >= 0.7) {
            ArrayList<HashNode<K, V> > temp = bucketArray;
            bucketArray = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;
            for (int i = 0; i < numBuckets; i++)
                bucketArray.add(null);
 
            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
        
        
        
        
    }
    
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.add("this", 1);
        map.add("coder", 2);
        map.add("this", 4);
        map.add("hi", 5);
        System.out.println(map.size());
        System.out.println(map.remove("this"));
        System.out.println(map.remove("this"));
        System.out.println(map.size());
        System.out.println(map.isEmpty());
    }
}
