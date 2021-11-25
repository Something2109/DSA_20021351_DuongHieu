import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class App {

    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {
        // Write your code here
        ArrayList<Integer> missing = new ArrayList<>();
        Hashtable<Integer, Integer> ht = new Hashtable<>();
        for (Integer n : arr) {
            if (!ht.containsKey(n)) {
                ht.put(n, 1);
            } else {
                ht.replace(n, ht.get(n) + 1);
            }
        }
        for (Integer n : brr) {
            if (!ht.containsKey(n)) {
                if (!duplicate(missing, n)) {
                    missing.add(n);
                }
            } else {
                ht.replace(n, ht.get(n) - 1);  
            }
            ht.remove(n, 0);
        }
        Collections.sort(missing);
        return missing;
    }

    public static List<Integer> missingNumbers2(List<Integer> arr, List<Integer> brr) {
        ArrayList<Integer> missing = new ArrayList<>();
        Collections.sort(arr);
        Collections.sort(brr);
        int i = 0;
        for (Integer n : brr) {
            if (i >= arr.size() || !arr.get(i).equals(n)) {
                if (!duplicate(missing, n)) {
                    missing.add(n);  
                }
            } else {
                i++;
            }
        }
        return missing;
    }

    private static boolean duplicate(List<Integer> missing, Integer n) {
        for (Integer m : missing) {
            if (Integer.compare(m, n) == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        In in = new In("In.txt");
        int n = in.readInt();
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> brr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(in.readInt());
        }
        n = in.readInt();
        for (int i = 0; i < n; i++) {
            brr.add(in.readInt());
        }
        for (Integer a : missingNumbers(arr, brr)) {
            StdOut.print(a + " ");
        }
    }
}