package Week9;
import java.util.ArrayDeque;
import java.util.Stack;

class Node {
    Node left;
    Node right;
    int data;
    
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class Traversal {

    public static void inOrder(Node root) {
        Node print = root;
        if (print.left == null) {
            System.out.print(print.data + " ");
        } else {
            inOrder(root.left);
            System.out.print(root.data + " ");
        }
        if (root.right != null) {
            inOrder(root.right);
        }
    }

    public static void inOrder2(Node root) {
        Stack<Node> st = new Stack<Node>();
        Node search = root;
        while (search != null) {
            st.push(search);
            search = search.left;
        }
        while (!st.isEmpty()) {
            search = st.pop();
            System.out.print(search.data + " ");
            search = search.right;
            while (search != null) {
                st.push(search);
                search = search.left;
            }
        }
    }

    public static void preOrder(Node root) {
        Node print = root;
        System.out.print(print.data + " ");
        if (print.left != null)  {
            preOrder(root.left);
        }
        if (root.right != null) {
            preOrder(root.right);
        }
    }

    public static void preOrder2(Node root) {
        Stack<Node> st = new Stack<Node>();
        Node search = root;
        while (search != null) {
            st.push(search);
            System.out.print(search.data + " ");
            search = search.left;
        }
        while (!st.isEmpty()) {
            search = st.pop();
            search = search.right;
            while (search != null) {
                st.push(search);
                System.out.print(search.data + " ");
                search = search.left;
            }
        }
    }

    public static void levelOrder(Node root) {
        ArrayDeque<Node> q = new ArrayDeque<Node>();
        q.add(root);
        while (!q.isEmpty()) {
            Node print = q.remove();
            System.out.print(print.data + " ");
            if (print.left != null) {
                q.add(print.left);
            }
            if (print.right != null) {
                q.add(print.right);
            }
        }
      
    }
}
