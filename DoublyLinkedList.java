//Creates a doubly linked list
public class DoublyLinkedList
{
/********************* Doubly Link List Node *********************/
    //Creates a node for the doubly linked list
    public class Node
    {
        private Integer value;
        private Node next;
        private Node prev;
        
        //Constructor for class Node.
        public Node(Integer value)
        {
            this.value = value;
            next = null;
            prev = null;
        }
        
        //Changes the next node.
        public void setNext(Node next)
        {
            this.next = next;
        }
        
        //Gets the next node.
        public Node getNext()
        {
            return next;
        }
        
        //Gets the value in a node.
        public Integer getValue()
        {
            return value;
        }
        
        //Changes the previous node.
        public void setPrev(Node prev)
        {
            this.prev = prev;
        }
        
        //Gets the previous node.
        public Node getPrev()
        {
            return prev;
        }
    }
    
/***************** Different Methods Used *****************/
    //Removes a node from doubly linked list.
    public void sortedRemove(int value) throws Exception
    {
        Node currNode = listHead.next;        
        while((currNode != listHead) && (currNode.getValue() != value)) {  //Keeps searching until value is found
            currNode = currNode.getNext();
        }
        if(currNode == listHead) {
            throw new Exception("Unable to find node with value: " + value + "\n");
        }
        //Fixes the references
        Node prevNode = currNode.getPrev();
        Node nextNode = currNode.getNext();
        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);
    }
    
    //Adds a node to the doubly linked list.
    public void sortedAdd(int value)
    {
        Node nodeToAdd = new Node(value);
        Node currNode = listHead.getNext();
        //Keeps going through the doubly linked list until correct node is reached
        while((currNode != listHead) && (currNode.getNext() != listHead) && ((currNode.getValue() > value) || (currNode.getNext().getValue() <= value))) {
            currNode = currNode.getNext();
        }
        if(currNode!= listHead && currNode.getValue() > value) {
            currNode = currNode.getNext();
        }
        //Fixes the references
        Node nextNode = currNode.getNext();
        currNode.setNext(nodeToAdd);
        nodeToAdd.setNext(nextNode);
        nodeToAdd.setPrev(currNode);
        nextNode.setPrev(nodeToAdd);
    }
    
    // Print the doubly linked list
    public void print()
    {
        Node tempNode = listHead.getNext();
        while(tempNode.getValue() != null) {
            System.out.print(tempNode.getValue() + " ");
            tempNode = tempNode.getNext();
        }
        System.out.println();
    }
    
/***************** Main Function and DoublyLinkedList Set Up *****************/
    Node dummy;
    Node listHead;
    
    //Constructor for DoublyLinkedList.
    public DoublyLinkedList()
    {
        //Initializes the doubly linked list.
        dummy = new Node(null);
        listHead = dummy;
        dummy.setNext(dummy);
        dummy.setPrev(dummy);
        
        //Adds values given in instructions.
        sortedAdd(1);        
        sortedAdd(5);
        sortedAdd(7);
        sortedAdd(9);
        sortedAdd(3);
        sortedAdd(2);
        
        System.out.println(" After all insertions: ");
        print();
        
        //Try is needed to catch a false remove(in this case; 4)
        try {
             System.out.println("\n After 3 and 7 deletion: ");
             sortedRemove(3);
             sortedRemove(7);
             
             print();
             
             System.out.println("\n After 4 deletion: ");
             sortedRemove(4);
             
             print();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Main function
    public static void main(String[] args) 
    {
        new DoublyLinkedList();
    }
}