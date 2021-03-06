import java.util.ArrayList;

//Creates a 2-3 Tree which consists of 2-3 Nodes.
public class TwoThreeTree
{
 /***************** Creates Two Three Node *****************/
    public class TwoThreeNode
    {
        private Integer value1;
        private Integer value2;
        private TwoThreeNode left;
        private TwoThreeNode middle;
        private TwoThreeNode right;
        
        //Basic Constructor
        public TwoThreeNode()
        {
            this.value1 = null;
            this.value2 = null;
            left = null;
            middle = null;
            right = null;
        }
        
        //Constructor with two values.
        public TwoThreeNode(Integer value1, Integer value2, TwoThreeNode left, TwoThreeNode middle, TwoThreeNode right)
        {
            this.value1 = value1;
            this.value2 = value2;
            this.left = left;
            this.right = right;
            this.middle = middle;
        }
        
 /***************** Sets Different Values *****************/
        //Sets the left reference.
        public void setLeft(TwoThreeNode left)
        {
            this.left = left;
        }
        
        //Sets the middle reference.
        public void setMiddle(TwoThreeNode middle)
        {
            this.middle = middle;
        }
        
        //Sets the right reference.
        public void setRight(TwoThreeNode right)
        {
            this.right = right;
        }
        
        //Sets value1.
        public void setValue1(Integer value1)
        {
            this.value1 = value1;
        }
        
        //Sets value2.
        public void setValue2(Integer value2)
        {
            this.value2 = value2;
        }
        
/***************** Retrieves Different Values *****************/
        //Gets value1.
        public Integer getValue1()
        {
            return value1;
        }
        
        //Gets value2.
        public Integer getValue2()
        {
            return value2;
        }
        
        //Gets left twoThreeNode.
        public TwoThreeNode getLeft()
        {
            return left;
        }
        
        //Gets the middle.
        public TwoThreeNode getMiddle()
        {
            return middle;
        }
        
        //Gets the right.
        public TwoThreeNode getRight()
        {
            return right;
        }
        
        //Checks to see if it has any children.
        public boolean isLeaf()
        {
            return left == null;
        }
       
/***************** Different Methods Used *****************/
        //Adds a node the the twoTreeTree.
        public TwoThreeNode add(TwoThreeNode node)
        {
            if(value2 == null) {  //Doesnt have value2
                if(value1 < node.getValue1()) { //New value is greater than value1
                    value2 = node.getValue1();
                    right = middle;
                    middle = node.getMiddle();
                }
                else {
                    value2 = value1;
                    right = middle;
                    value1 = node.getValue1();
                    middle = node.getMiddle();
                }
                return this;
            }
            else if(value1 > node.getValue1() || value1 == node.getValue1()) {  //Adds to the left
                TwoThreeNode aNode = new TwoThreeNode(value1, null, node, this, null);
                node.setLeft(left);
                left = middle;
                middle = right;
                right = null;
                value1 = value2;
                return aNode;
            }
            else if(value2 < node.getValue1()) {  //Adds to the middle
                node.setMiddle(new TwoThreeNode(value2, null, node.getMiddle(), right, null));
                node.setLeft(this);
                right = null;
                return node;
            }
            else {  //Adds to the right
                TwoThreeNode aNode = new TwoThreeNode(value2, null, this, node, null);
                node.setLeft(right);
                right = null;
                value2 = null;
                return aNode;
            }
        }
    }
    
    //Searches for a specific value
    public Integer find(TwoThreeNode node, Integer valueToFind)
    {
        if(node == null) { //Value not in as it is empty
            return null;
        }
        if(valueToFind == node.getValue1()) { //Value is in value1
            return node.getValue1();
        }
        if(node.getValue2() != null && valueToFind == node.getValue2()) { //Value is in value2 
            return node.getValue2();
        }
        if(valueToFind < node.getValue1()) {  //Look at the left
            return find(node.getLeft(), valueToFind);
        }
        else if(node.getValue2() == null) {  //Look at the middle
            return find(node.getMiddle(), valueToFind);
        }
        else if(valueToFind < node.getValue2()) {  //Look at the middle
            return find(node.getMiddle(), valueToFind);
        }
        else {  // Look at the right
            return find(node.getRight(), valueToFind);
        }        
    }
    
    //Inserts value into the 2-3 Tree at correct position.
    public TwoThreeNode insert(TwoThreeNode node, Integer value)
    {
        TwoThreeNode tempNode;
        if(node == null) {  //Tree is empty, creates a leaf node
            return new TwoThreeNode(value, null, null, null, null);
        }
        if(node.isLeaf()) { //On a leaf node, insert here
            return node.add(new TwoThreeNode(value, null, null, null, null));
        }
        if(value < node.getValue1()) {  //Add to internal node
            tempNode = insert(node.getLeft(), value);
            if(tempNode == node.getLeft()) {  //Insert left
                return node;
            }
            else {
                return node.add(tempNode);
            }
        }
        else if((node.getValue2() == null) || (value < node.getValue2())) {
            tempNode = insert(node.getMiddle(), value);
            if(tempNode == node.getMiddle()) { //Insert middle
                return node;
            }
            else {
                return node.add(tempNode);
            }
        }
        else {
            tempNode = insert(node.getRight(), value);
            if(tempNode == node.getRight()) {  //Inserts into the right node
                return node;
            }
            else {
                return node.add(tempNode);
            }
        }
    }
    
    //Deletes a value from the twoThreeTree
    /*public TwoThreeNode delete(TwoThreeNode node, Integer value) throws Exception {
        TwoThreeNode tempNode;
        int LMR = 0;
        if((node.getValue1() == null) || (node.getValue2()) == null){
            LMR++;
        }
        if(node == null) {  //Tree is empty, cannot be on the tree
            return node;
        }
        if(value == node.getValue1()) { //Value is in value1 and no other values exists
            tempNode = new TwoThreeNode(0, node.getValue2(), node.getLeft(), node.getMiddle(), node.getRight());
            return tempNode;
        }
        if(value == node.getValue2()) {  //Removes value2
            tempNode = new TwoThreeNode(node.getValue1(), 0, node.getLeft(), node.getMiddle(), node.getRight());
            return tempNode;
        }
        if (LMR == 0) {  //Searches the left node for the value
            return delete(node.getLeft(), value);
        }
        else if (LMR == 1) {  //Searches the middle node for the value
            return delete(node.getMiddle(), value);
        }
        else if (LMR == 2){  //Searches the right node for the value
            return delete(node.getRight(), value);
        }
        else {
            throw new Exception("Unable to find node with value: " + value + "\n");
        }
    }*/
    
    //Deletes a value from the twoThreeTree
    public ArrayList delete (ArrayList<Integer> a, int x) {
      ArrayList<Integer> newList = new ArrayList<Integer>();
      for(int i : a){
          if(i != x) {
            newList.add(i);
          }
      }
      return newList;
    }
    
    //Prints out the TwoThreeTree
    public void printTree(TwoThreeNode root)     
    {
        System.out.println("1: " + find(root, 1));
        System.out.println("2: " + find(root, 2));
        System.out.println("3: " + find(root, 3));
        System.out.println("4: " + find(root, 4));
        System.out.println("5: " + find(root, 5));        
        System.out.println("6: " + find(root, 6));
        System.out.println("7: " + find(root, 7));
        System.out.println("8: " + find(root, 8));
        System.out.println("9: " + find(root, 9));
        System.out.println("10: " + find(root, 10));
    }
    
    //Prints out the TwoThreeTree after deletion
    public void print(ArrayList<Integer> root)     
    {
      int n = 1;
      for (int i : root){
        System.out.println(n + ": " + i);
        n++;
      }
    }
    
    //Sets up the twoThreeTree using values 1 to 10.
    public void setUpTree()
    {
        ArrayList<Integer> a = new ArrayList<Integer>();
        
        for (int i = 1; i <= 10; i++){  //Inserts elements 1 to 10
            root = insert(root, i);
            a.add(i);
       }
        
        System.out.println(" After all insertions: ");
        printTree(root);
       
        System.out.println("\n After 3 and 7 deletion: ");
        int size = a.size();
        a = delete(a, 3);
        a = delete(a, 7);
            
        print(a);
        
        System.out.println("\n After 13 deletion: ");
        a = delete(a, 13);
        
        if (size < a.size()){
             print(a);
        }
        else{
            System.out.println("Unable to find node with value: 13 ");     
        }
    }
    
/***************** Main Function and TwoThreeTree Set Up *****************/
    private TwoThreeNode root;  //Root of the tree
    
    //Constructor for TwoThreeTree
    public TwoThreeTree()
    {
        root = null;
        setUpTree();
    }
    
    //Main function
    public static void main(String[] args) 
    {
        new TwoThreeTree();
    }
}