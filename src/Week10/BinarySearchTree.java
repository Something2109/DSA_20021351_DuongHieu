package Week10;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Stack;

class Node {
    Node left;
    Node right;
    int ht;
    int data;
    
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class BinarySearchTree {

    /** Height of a Binary Tree. */
    public static int height(Node root) {
        // Write your code here.
      if (root == null) {
          return -1;
      }
      int leftHeight = 1 + height(root.left);
      int rightHeight = 1 + height(root.right);
      return Math.max(leftHeight, rightHeight);
    }

    /** Binary Search Tree : Insertion. */
    public static Node insert(Node root,int data) {
        if (root == null) {
            root = new Node(data);
        } else if (data < root.data) {
            if (root.left == null) {
                root.left = new Node(data);
            } else {
                insert(root.left, data);
            }
        } else {
            if (root.right == null) {
                root.right = new Node(data);
            } else {
                insert(root.right, data);
            }
        }
    	return root;
    }

    /** Binary Search Tree : Lowest Common Ancestor. */
    public static Node lca(Node root, int v1, int v2) {
        // Write your code here.
      if (v1 == root.data || v2 == root.data) {
          return root;
      } else if (v1 < root.data ^ v2 < root.data) {
          return root;
      } else if (v1 < root.data && v2 < root.data) {
          return lca(root.left, v1, v2);
      } else {
          return lca(root.right, v1, v2);
      }
    }

    boolean checkBST(Node root) {
        Stack<Node> queue = new Stack<Node>();
        queue.push(root);
        ArrayList<Integer> data = new ArrayList<Integer>();
        Node query = root.left;
        while (query.left != null) {
            queue.push(query);
            query = query.left;
        }
        data.add(query.data);
        while (!queue.isEmpty()) {
            query = queue.pop();
            data.add(query.data);
            query = query.right;
            while (query != null) {
                queue.push(query);
                query = query.left;
            }
        }
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i) <= data.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    static Node insertBalance(Node root,int val)
    {
    	if (root == null) {
            root = new Node(val);
            root.ht = height(root);
            return root;
        } else if (val > root.data) {
            root.right = insert(root.right, val);
        } else if (val < root.data) {
            root.left = insert(root.left, val);
        }
        int balance = height(root.left) - height(root.right);
        if(balance > 1) {
           if(height(root.left.left) >= height(root.left.right)) {
               root = rightRotation(root);
           }
           else {
               root.left = leftRotation(root.left);
               root = rightRotation(root);
           }
        }
        else if(balance < -1) {
            if(height(root.right.right) >= height(root.right.left)) {
                root = leftRotation(root);
            }
            else {
                root.right = rightRotation(root.right);
                root = leftRotation(root);
            }
        }
        else {
            root.ht = height(root);
        }
        
        return root;
    }
    private static Node rightRotation(Node root) {
       Node newRoot = root.left;
       root.left = newRoot.right;
       newRoot.right = root;
       root.ht = height(root);
       newRoot.ht = height(newRoot);
       return newRoot;
    }

    private static Node leftRotation(Node root) {
        Node newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        root.ht = height(root);
        newRoot.ht = height(newRoot);
        return newRoot;
    }
}