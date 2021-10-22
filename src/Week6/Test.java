package Week6;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Test {

    static final String RANDOM = "random";
    static final String SORTED = "sorted";
    static final String REVERSED = "reversed";
    static final String EQUAL = "equal";
    static final int TEST_TRY = 5;
    static final int DELAY = 10;

    static void test(int n, int testTime, PrintWriter output, String str) {
        Integer[] arr;
        switch(str) {
            case RANDOM: {
                output.println((n / 1000) + "k Arrays:");
                StdOut.println((n / 1000) + "k Arrays:");
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
        randomizedQuickTest(arr, testTime, output);
        quickTest(arr, testTime, output);
        output.println();
        StdOut.println();
        output.flush();
    }

    static void insertionTest(Integer[] arr, int testTime, PrintWriter output) {
        output.print("Insertion sort: ");
        StdOut.print("Insertion sort: ");
        long total = 0;
        for (int i = 0; i < testTime + TEST_TRY; i++) {
            Integer[] test = Arrays.copyOf(arr, arr.length);
            long time = Sort.insertionSort(test);
            
            if (i >= TEST_TRY) {
                total += time;
                output.print(time + " ");
            }
            StdOut.print(time + " ");
        } 
        total = Math.round((double) total / testTime);
        output.print("\nAverage: " + total +"\n");
        StdOut.print("\nAverage: " + total +"\n");
    }

    static void mergeTest(Integer[] arr, int testTime, PrintWriter output) {
        output.print("Merge sort: ");
        StdOut.print("Merge sort: ");
        long total = 0;
        for (int i = 0; i < testTime + TEST_TRY; i++) {
            Integer[] test = Arrays.copyOf(arr, arr.length);
            long time = Sort.mergeSort(test);
            
            if (i >= TEST_TRY) {
                total += time;
                output.print(time + " ");
            }
            StdOut.print(time + " ");
        } 
        total = Math.round((double) total / testTime);
        output.print("\nAverage: " + total +"\n");
        StdOut.print("\nAverage: " + total +"\n");
    }

    static void randomizedQuickTest(Integer[] arr, int testTime, PrintWriter output) {
        output.print("Randomized Quick sort: ");
        StdOut.print("Randomized Quick sort: ");
        long total = 0;
        for (int i = 0; i < testTime + TEST_TRY; i++) {
            Integer[] test = Arrays.copyOf(arr, arr.length);
            long time = Sort.randomizedQuickSort(test);
            
            if (i >= TEST_TRY) {
                total += time;
                output.print(time + " ");
            }
            StdOut.print(time + " ");
        } 
        total = Math.round((double) total / testTime);
        output.print("\nAverage: " + total +"\n");
        StdOut.print("\nAverage: " + total +"\n");
    }

    static void quickTest(Integer[] arr, int testTime, PrintWriter output) {
        output.print("Quick sort: ");
        StdOut.print("Quick sort: ");
        long total = 0;
        for (int i = 0; i < testTime + TEST_TRY; i++) {
            Integer[] test = Arrays.copyOf(arr, arr.length);
            long time = Sort.quickSort(test);
            
            if (i >= TEST_TRY) {
                total += time;
                output.print(time + " ");
            }
            StdOut.print(time + " ");
        } 
        total = Math.round((double) total / testTime);
        output.print("\nAverage: " + total +"\n");
        StdOut.print("\nAverage: " + total +"\n");
    }

    public static void main(String[] args) {
        int testTime = 10;
        int n = 20000;
        try {
            FileWriter fw = new FileWriter(".\\src\\Week6\\Result.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter output = new PrintWriter(bw);

            // test(n, testTime, output, "1Kints.txt");
            // test(n, testTime, output, "2Kints.txt");
            // test(n, testTime, output, "4Kints.txt");
            // test(n, testTime, output, "8Kints.txt");
            // test(n, testTime, output, "16Kints.txt");
            // test(n, testTime, output, "32Kints.txt");
            // test(n, testTime, output, RANDOM);
            test(n, testTime, output, SORTED);
            test(n, testTime, output, REVERSED);
            test(n, testTime, output, EQUAL);

            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
