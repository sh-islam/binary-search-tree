import java.util.LinkedList;
import java.util.Queue;

public class BST {
    public Node root;  // BST contains a root node "root"

    public class Node { // BST is made up of nodes with attributes
        int data;
        Node left;
        Node right;

        Node(int d) {data = d;}
    }

    // Insert method O(n * log n) n elements of dataArr * log n operation cost
    void insert(int[] dataArray) {
        for (int data : dataArray) {
            // Create new node if root of BST is empty
            if (root == null) {
                root = new Node(data);
                continue;
            }

            // Place other nodes left or right of parent node
            Node currentNode = root;
            while (true) {
                if (data > currentNode.data) {                  // Searching log n nodes
                    if (currentNode.right == null) {
                        currentNode.right = new Node(data);
                        break;  // After insertion of node, return to outer for loop to get next int val from dataArray
                    }
                    currentNode = currentNode.right;
                } else {
                    if (currentNode.left == null) {            // Searching log n nodes
                        currentNode.left = new Node(data);
                        break;
                    }
                    currentNode = currentNode.left;
                }
            }
        }
    }

    // O(n)
    int getHeight(BST.Node node) {
        if (node == null) {
            return 0;
        } else if (node.left == null && node.right == null) {
            // Leaf node
            return 0;
        } else {
            int leftHeight = getHeight(node.left);
            int rightHeight = getHeight(node.right);

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // All prints have a O(n) time complexity, as we need to visit each node once
    public void preOrderPrint(Node node) {
        if (node == null) return;

        System.out.print(node.data + " "); // Print root node
        preOrderPrint(node.left); // Print left and right children recursively
        preOrderPrint(node.right);

        // Add a new line after the entire traversal is complete
        if (node == root) System.out.println(); 
    }

    public void postOrderPrint(Node node){
        if (node == null) return;
        
        postOrderPrint(node.left);
        postOrderPrint(node.right);
        System.out.print(node.data + " ");

        if (node == root) System.out.println();
    }

    public void inOrderPrint(Node node){
        inOrder(node);
        System.out.println();    
    }

    private void inOrder(Node node){
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public void levelOrderPrint(){
        Queue<Node> q =  new LinkedList<Node>();
        q.add(this.root);   // Add root of the node to head of queue

        while (q.size() != 0){
            Node popped = q.remove();
            if (popped != null) {
                System.out.print(popped.data + " ");
                q.add(popped.left);
                q.add(popped.right);
            }
        }
        System.out.println();
    }

    void printTree(BST.Node node, String indent, boolean last) {
        if (node != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("└─");
                indent += "  ";
            } else {
                System.out.print("├─");
                indent += "│ ";
            }
            System.out.println(node.data);
            printTree(node.left, indent, false);
            printTree(node.right, indent, true);
        }
    }

    // Construct a bst given its preorder traversal
    void preOrderInsert(int [] dataArr){
        insert(dataArr);    // Preorder insertion is just regular BST insertion
    }

    // Construct a bst given its inorder traversal
    void postOrderInsert(int [] dataArr){
        int[] newArr = new int[dataArr.length];
        int j = 0;
        for (int i = dataArr.length - 1; i >= 0; i--){
            newArr[j] = dataArr[i];
            j++;
        }
        insert(newArr);
    }

    // Construct a full bst given its in order traversal with 2^k -1 elements 
    public void inOrderInsertFull(int [] dataArr){
       // for a full bst we need 2^k - 1 elements
        int arrLen = dataArr.length;
        int k =0;

        while (arrLen >0){
            arrLen/=2;
            k++;
        }

        int expectedLength = (int) Math.pow(2, k) - 1;
        if (dataArr.length !=expectedLength) {
            System.out.println("For a full bst we need 2^k - 1 elements");
            return;
        }
        this.root = inOrderInsertFullHelper(dataArr, 0, dataArr.length - 1);
    }

    // Recursively assign root of subtree to dataArr[midIndex] O(n)
    private Node inOrderInsertFullHelper(int[] dataArr, int startIndex, int endIndex) {
        if (startIndex == endIndex) return new Node(dataArr[startIndex]);
        int midIndex = (startIndex + endIndex) / 2; 
        Node node = new Node(dataArr[midIndex]);    
        node.left = inOrderInsertFullHelper(dataArr, startIndex, midIndex - 1);
        node.right = inOrderInsertFullHelper(dataArr, midIndex + 1, endIndex);
    
        return node;
    }
    
    // Construct a bst given its level order O(n log n)
    public void levelOrderInsert(int [] dataArr){
        insert(dataArr);    // Level order insertion is just regular BST insertion
    }

    
    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(new int[] {4,5,3,2});
        bst.preOrderPrint(bst.root);    // 4,3,2,5
        bst.postOrderPrint(bst.root);   // 2,3,5,4
        bst.inOrderPrint(bst.root);     // 2 3 4 5
        bst.levelOrderPrint();          // 4,3,5,2 
        bst.printTree(bst.root, "", true);
        System.out.println("Height of bst: " + bst.getHeight(bst.root));

        BST bstPreOrder = new BST();
        int [] preOrderArr = {4,3,2,5};     // creating a bst from preorder traversal
        bstPreOrder.preOrderInsert(preOrderArr); 
        bstPreOrder.preOrderPrint(bstPreOrder.root);    // preorder traversal prints the same traversal inputted: 4,3,2,5

        BST bstPostOrder = new BST();
        int [] postOrderArr = {2,3,5,4};    // creating a bst from postorder traversal
        bstPostOrder.postOrderInsert(postOrderArr);     // postorder traversal prints the same traversal inputted: 2,3,5,4
        bstPostOrder.postOrderPrint(bstPostOrder.root);

        BST bstLevelOrder = new BST();
        int [] levelOrderArr = {4,3,5,2};    // creating a bst from levelorder traversal
        bstLevelOrder.levelOrderInsert(levelOrderArr);      // level order traversal prints the same traversal inputted: 4,3,5,2
        bstLevelOrder.levelOrderPrint();

        BST bstInOrder = new BST();
        int [] inOrderArr = {10,20,30,100,150,200,300};
        bstInOrder.inOrderInsertFull(inOrderArr);
        bstInOrder.inOrderPrint(bstInOrder.root);
    }


}