//{ Driver Code Starts
//Initial Template for Java

/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class Node {
    int data;
    Node left, right;
    
    public Node(int data){
        this.data = data;
        left = right = null;
    }
}

public class Main {
    
	public static void main (String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0; i<n; i++){
                arr[i] = sc.nextInt();
            }
            GFG obj = new GFG();
            Node tree = obj.constructBST(arr);
            preorder(tree);
            System.out.println();
        }
	}
	
	 public static void preorder(Node root){
        if(root == null){
            return;
        }
        
        System.out.print(root.data+" ");
        preorder(root.left);
        preorder(root.right);
    }
}


// } Driver Code Ends


//User function Template for Java


class GFG 
{
    //Function to construct the BST from its given level order traversal.
    public Node constructBST(int[] arr){
        // first element in arr is the root of BST
        Node root = new Node(arr[0]);
        for (int i = 1; i < arr.length; i++){
            Node currentNode = root;
            int data = arr[i];
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
        return root;
    }
}