package Week5;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;

public class SelectionSort {
    static long sort(int[] arr) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int change = arr[i];
            arr[i] = arr[min];
            arr[min] = change;
        }
        long end = System.currentTimeMillis() - start;
        return end;
    }

    public static void main(String[] args) {
        int n = 2000;
        double test = 100;
        long total = 0;
        int[] arr = new int[n];
        for (int j = 0; j < test; j++) {
            boolean[] check = new boolean[n];
            for (int i = 0; i < arr.length; i++) {
                do {
                    arr[i] = (int) Math.round(Math.random() * (n - 1));
                } while (check[arr[i]]);
                check[arr[i]] = true;
            }

            total += sort(arr);
        }
        StdOut.println(total / test);
        total = 0;
        for (int j = 0; j < test; j++) {
            boolean[] check = new boolean[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) Math.round(Math.random() * (n - 1));
                check[arr[i]] = true;
            }

            total += sort(arr);
        }
        StdOut.print(total / test);
    }
}
