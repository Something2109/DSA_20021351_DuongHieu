package Week9;

import java.util.ArrayList;
import java.util.List;

public class ClosestNumber {
    private static void merge(List<Integer> a, ArrayList<Integer> aux, int lo, int mid, int hi) {
        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux.set(k, a.get(k)); 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {             
                a.set(k, aux.get(j));
                j++; 
            }
            else if (j > hi) {
                a.set(k, aux.get(i));
                i++;
            } else if (aux.get(j)< aux.get(i)) { 
                a.set(k, aux.get(j));
                j++;
            } else {
                a.set(k, aux.get(i));
                i++;
            }
        }
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(List<Integer> a, ArrayList<Integer> aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void sort(List<Integer> a) {
        ArrayList<Integer> aux = new ArrayList<Integer>();
        for (int i = 0; i < a.size(); i++) {
            aux.add(a.get(i));
        }
        sort(a, aux, 0, a.size() - 1);
    }

    public static List<Integer> closestNumbers(List<Integer> arr) {
        // Write your code here
        sort(arr);
        ArrayList<Integer> minimum = new ArrayList<Integer>();
        if (arr.size() > 1) {
            int min = arr.get(1) - arr.get(0) ;
            for (int i = 1; i < arr.size(); i++) {
                int temp = arr.get(i) - arr.get(i - 1);
                if (temp < min) {
                    min = temp;
                    minimum.clear();
                    minimum.add(arr.get(i - 1));
                    minimum.add(arr.get(i));
                } else if (temp == min) {
                    minimum.add(arr.get(i - 1));
                    minimum.add(arr.get(i));
                }
            }
        }
        return minimum;
    }
}
