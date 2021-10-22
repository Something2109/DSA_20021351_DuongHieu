package Week6;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Sort {

    static long insertionSort(Comparable[] a) {
        long start = System.currentTimeMillis();
        Insertion.sort(a);
        long time = System.currentTimeMillis() - start;
        return time;
    }

    static long mergeSort(Comparable[] a) {
        long start = System.currentTimeMillis();
        Merge.sort(a);
        long time = System.currentTimeMillis() - start;
        return time;
    }

    static long randomizedQuickSort(Comparable[] a) {
        long start = System.currentTimeMillis();
        Quick.sort(a);
        long time = System.currentTimeMillis() - start;
        return time;
    }

    static long quickSort(Comparable[] a) {
        long start = System.currentTimeMillis();
        QuickSort.sort(a);
        long time = System.currentTimeMillis() - start;
        return time;
    }

    static Integer[] randomArr(int n) {
        boolean[] check = new boolean[n];
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            do {
                int a = (int) Math.round(Math.random() * (n - 1));
                arr[i] = Integer.valueOf(a);
            } while (check[arr[i]]);
            check[arr[i]] = true;
        }
        return arr;
    }

    static Integer[] sortedArr(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.valueOf(i + 1);
        }
        return arr;
    }

    static Integer[] reversedArr(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.valueOf(n - i);
        }
        return arr;
    }

    static Integer[] equalArr(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.valueOf(n);
        }
        return arr;
    }

    static int[] fileInput(String fileName) {
        String name = "C:\\Users\\hieud\\Documents\\Code\\algs4-data\\" + fileName;
        In in = new In(name);
        int[] arr = in.readAllInts();
        return arr;
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = reversedArr(n);
        long insertionTime = 0,
            mergeTime = 0,
            quickTime = 0;

        insertionTime = insertionSort(Arrays.copyOf(arr, n));
        mergeTime = mergeSort(Arrays.copyOf(arr, n));
        quickTime = randomizedQuickSort(Arrays.copyOf(arr, n));
    
        StdOut.println("Insertion run time: " + insertionTime);
        StdOut.println("Merge run time: " + mergeTime);
        StdOut.println("Quick run time: " + quickTime);
    }
}
