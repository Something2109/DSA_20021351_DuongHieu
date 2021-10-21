package Week6;
import java.io.PrintStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import edu.princeton.cs.algs4.*;

public class Test {

    static final String RANDOM = "random";
    static final String SORTED = "sorted";
    static final String REVERSED = "reversed";
    static final String EQUAL = "equal";

    static void test(int n, int testTime, PrintStream output, String str) {
        Integer[] arr;
        switch(str) {
            case RANDOM: {
                output.println("Random Arrays:");
                StdOut.println("Random Arrays:");
                arr = Sort.randomArr(n);
                break;
            }

            case SORTED: { 
                output.println("Sorted Arrays:");
                StdOut.println("Sorted Arrays:");
                arr = Sort.sortedArr(n);
                break;
            }

            case REVERSED: { 
                output.println("Reverse Sorted Arrays:");
                StdOut.println("Reverse Sorted Arrays:");
                arr = Sort.reversedArr(n); 
                break;
            }

            case EQUAL: { 
                output.println("Equal Arrays:");
                StdOut.println("Equal Arrays:");
                arr = Sort.equalArr(n);
                break;
            }

            default: {
                In file = new In("C:\\Users\\hieud\\Documents\\Code\\algs4-data\\" + str);
                int[] a = file.readAllInts();
                arr = new Integer[a.length];
                for (int i = 0; i < a.length; i++) {
                    arr[i] = Integer.valueOf(a[i]);
                }
                output.println(str + " Arrays:");
                StdOut.println(str + " Arrays:");
                break;
            }
        }
        insertionTest(arr, testTime, output);
        mergeTest(arr, testTime, output);
        quickTest(arr, testTime, output);
        output.println();
        StdOut.println();
    }

    static void insertionTest(Integer[] arr, int testTime, PrintStream output) {
        output.print("Insertion sort: ");
        StdOut.print("Insertion sort: ");
        Sort.insertionSort(Arrays.copyOf(arr, arr.length));
        long total = 0;
        for (int i = 0; i < testTime; i++) {
            long time = Sort.insertionSort(Arrays.copyOf(arr, arr.length));
            total += time;
            output.print(time + " ");
            StdOut.print(time + " ");
        } 
        total = Math.round((double) total / testTime);
        output.print("\nAverage: " + total +"\n");
        StdOut.print("\nAverage: " + total +"\n");
    }

    static void mergeTest(Integer[] arr, int testTime, PrintStream output) {
        output.print("Merge sort: ");
        StdOut.print("Merge sort: ");
        Sort.mergeSort(Arrays.copyOf(arr, arr.length));
        long total = 0;
        for (int i = 0; i < testTime; i++) {
            long time = Sort.mergeSort(Arrays.copyOf(arr, arr.length));
            total += time;
            output.print(time + " ");
            StdOut.print(time + " ");
        } 
        total = Math.round((double) total / testTime);
        output.print("\nAverage: " + total +"\n");
        StdOut.print("\nAverage: " + total +"\n");
    }

    static void quickTest(Integer[] arr, int testTime, PrintStream output) {
        output.print("Quick sort: ");
        StdOut.print("Quick sort: ");
        Sort.quickSort(Arrays.copyOf(arr, arr.length));
        long total = 0;
        for (int i = 0; i < testTime; i++) {
            long time = Sort.quickSort(Arrays.copyOf(arr, arr.length));
            total += time;
            output.print(time + " ");
            StdOut.print(time + " ");
        } 
        total = Math.round((double) total / testTime);
        output.print("\nAverage: " + total +"\n");
        StdOut.print("\nAverage: " + total +"\n");
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(".\\src\\Week6\\Sort.txt");
        PrintStream output = new PrintStream(file);
        int testTime = 10;
        int n = 50000;

        test(n, testTime, output, "1Kints.txt");
        test(n, testTime, output, "2Kints.txt");
        test(n, testTime, output, "4Kints.txt");
        test(n, testTime, output, "8Kints.txt");
        test(n, testTime, output, "16Kints.txt");
        test(n, testTime, output, "32Kints.txt");
        test(n, testTime, output, RANDOM);
        test(n, testTime, output, SORTED);
        test(n, testTime, output, REVERSED);
        test(n, testTime, output, EQUAL);

        output.close();
    }
}
