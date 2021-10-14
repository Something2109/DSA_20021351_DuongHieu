package Week5;
import java.util.Scanner;
import java.util.List;

import edu.princeton.cs.algs4.*;

public class Solution {
    static Scanner scanner = new Scanner(System.in);

    private static String isBalanced(String s) {
        // Write your code here
        char[] stack = new char[s.length()];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '[': case '{': case '(': {
                    stack[count] = s.charAt(i);
                    count++;
                    break;
                }
                case ']': {
                    if (count != 0 && stack[count - 1] == '[') {
                        count--;
                    } else {
                        return "NO";
                    }
                    break;
                }
                case '}': {
                    if (count != 0 && stack[count - 1] == '{') {
                        count--;
                    } else {
                        return "NO";
                    }
                    break;
                }
                case ')': {
                    if (count != 0 && stack[count - 1] == '(') {
                        count--;
                    } else {
                        return "NO";
                    }
                    break;
                }
            }
        }
        if (count == 0) {
            return "YES";
        } else {
            return "NO";
        }
    }

    private static void queueUsingTwoStacks() {
        Stack<Integer> enqueue = new Stack<Integer>();
        Stack<Integer> dequeue = new Stack<Integer>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int query = scanner.nextInt();
            switch (query) {
                case 1: {
                    int add = scanner.nextInt();
                    enqueue.push(add);
                    break;
                }
                case 2: {
                    if (dequeue.isEmpty()) {
                        while (!enqueue.isEmpty()) {
                            dequeue.push(enqueue.peek());
                            enqueue.pop();
                        }
                    }
                    dequeue.pop();
                    break;
                }
                case 3: {
                    if (dequeue.isEmpty()) {
                        while (!enqueue.isEmpty()) {
                            dequeue.push(enqueue.peek());
                            enqueue.pop();
                        }
                    }
                    System.out.println(dequeue.peek());
                    break;
                }
            }
        }
    }

    private static void simpleTextEditor() {
        String text = new String();
        Stack<String> action = new Stack<String>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int query = scanner.nextInt();
            switch (query) {
                case 1: {
                    action.push(text);
                    text = text + scanner.next();
                    break;
                }
                case 2: {
                    action.push(text);
                    text = text.substring(0, (text.length() - scanner.nextInt()));
                    break;
                }
                case 3: {
                    System.out.println(text.charAt(scanner.nextInt() - 1));
                    break;
                }
                case 4: {
                    text = action.peek();
                    action.pop();
                    break;
                }
            }
        }
    }

    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
        // Write your code here
        int h1Size = 0;
        int h2Size = 0;
        int h3Size = 0;
        for (int i = 0; i < h1.size(); i++) {
            h1Size += h1.get(i);
        }
        for (int i = 0; i < h2.size(); i++) {
            h2Size += h2.get(i);
        }
        for (int i = 0; i < h3.size(); i++) {
            h3Size += h3.get(i);
        }
        if (h1Size != h2Size || h2Size != h3Size || h1Size != h3Size) {
            while (!h1.isEmpty() && !h2.isEmpty() && !h3.isEmpty()) {
                while (!h2.isEmpty() && h2Size > h1Size) {
                    h2Size -= h2.get(0);
                    h2.remove(0);
                } 
                if (h2Size == h1Size) {
                    while (!h3.isEmpty() && h3Size > h2Size) {
                        h3Size -= h3.get(0);
                        h3.remove(0);
                        if (h3Size == h2Size) {
                            return h3Size;
                        }
                    } 
                }
                h1Size -= h1.get(0);
                h1.remove(0);
            }
        }
        return Math.min(h1Size, Math.min(h2Size, h3Size));
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        simpleTextEditor();
    } 
}
