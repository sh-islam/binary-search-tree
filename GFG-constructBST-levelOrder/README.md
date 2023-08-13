# Geeks for geeks problem: Construct BST from its given level order traversal
https://www.geeksforgeeks.org/construct-bst-given-level-order-traversal/

Given an a level order traversal construct a BST 
Input: arr[] = {7, 4, 12, 3, 6, 8, 1, 5, 10}
Output: BST: 
            7        
          /  \       
        4      12      
      /  \     /     
    3     6   8  
  /      /      \
1       5        10

Solution: regular BST insertion of average time complexity O(n * log n)
- Insert the first element in arr[] as root of tree
- Recursively find where to add node with data from arr[i] and add in tree (searching tree takes O(log n) average)
- N levels, where n is number of elements in arr with each level having average case O(log n) time complexity --> O(n log n)
