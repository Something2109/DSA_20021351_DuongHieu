import java.util.List;

public class Solution {
    public static void insertionSort1(int n, List<Integer> arr) {
        // Write your code here
        int i = n - 1;
        int a = arr.get(i);
        while (i > 0 && a < arr.get(i - 1)) {
            arr.set(i, arr.get(i - 1));
            for (int j = 0; j < n; j++) {
                System.out.print(arr.get(j) + " ");
            }
            System.out.print("\n");
            i--;
        }
        arr.set(i, a);
        for (int j = 0; j < n; j++) {
            System.out.print(arr.get(j) + " ");
        }
    }

    public static void insertionSort2(int n, List<Integer> arr) {
        // Write your code here
        for (int i = 2; i <= n; i++) {
            insertionSort1(i, arr);
            for (int j = 0; j < n; j++) {
                System.out.print(arr.get(j) + " ");
            }
            System.out.print("\n");
        }
    }

    public static void insertionSort(int[] A){
        for(int i = 1; i < A.length; i++){
            int value = A[i];
            int j = i - 1;
            while(j >= 0 && A[j] > value){
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = value;
        }

        printArray(A);
    }

    static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
    }
}
