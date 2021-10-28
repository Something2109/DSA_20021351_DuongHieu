import java.util.ArrayList;
import java.util.List;

class Result {

    /** Intro to Tutorial Challenges */
    public static int introTutorial(int V, List<Integer> arr) {
        // Write your code here
            int lo = 0;
            int hi = arr.size() - 1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (V < arr.get(mid)) {
                    hi = mid - 1;
                } else if (V > arr.get(mid)) {
                    lo = mid + 1;
                } else {
                    return mid;
                }
            }
            return -1;
        }

    /** Counting Sort 1. */
    public static List<Integer> countingSort(List<Integer> arr) {
        ArrayList<Integer> count = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            count.add(0);
        }
        for (int i = 0; i < arr.size(); i++) {
            count.set(arr.get(i), count.get(arr.get(i)) + 1);
        }
        return count;
    }

    /** QuickSort 1 - Partition. */
    public static List<Integer> quickSort(List<Integer> arr) {
    // Write your code here
        List<Integer> array = new ArrayList<Integer>();
        int lo = 0;
        int eq = 0;
        int hi = 0;
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(0) > arr.get(i)) {
                array.add(lo, arr.get(i));
                lo++;
                eq++;
                hi++;
            } else if (arr.get(0) > arr.get(i)) {
                array.add(hi, arr.get(i));
                hi++;
            } else {
                array.add(eq, arr.get(i));
                eq++;
                hi++;
            }
        }
        array.add(lo, arr.get(0));
        return array;
    }
}
