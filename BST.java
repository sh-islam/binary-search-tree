public class BST {
    public Node root;  // BST contains a root node "root"

    public class Node { // BST is made up of nodes with attributes
        int data;
        Node left;
        Node right;

        Node(int d) {
            data = d;
        }
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

    public void preOrderPrint(Node node) {
        if (node == null) return;

        System.out.print(node.data + " "); // Print root node
        preOrderPrint(node.left); // Print left and right children recursively
        preOrderPrint(node.right);

        // Add a new line after the entire traversal is complete
        if (node == root) {
            System.out.println();
        }
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

    void preOrderInsert(int [] dataArr){
        root = new Node(dataArr[0]);
        int[] newArr = new int[dataArr.length - 1];
        System.arraycopy(dataArr, 1, newArr, 0, newArr.length);
        insert(newArr);
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(new int[] {4,5,3,2});
        bst.preOrderPrint(bst.root);
        bst.printTree(bst.root, "", true);

        BST bstPreOrder = new BST();
        int [] preOrderArr = {4, 5, 3, 1, 6};
        bstPreOrder.preOrderInsert(preOrderArr);
        bstPreOrder.preOrderPrint(bstPreOrder.root);
    }


}