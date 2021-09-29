package Week3;
import java.util.Arrays;

public class BinarySearch {
    public static int binarySearch(int[] a, int number) {
        int high = a.length,
            low = 0,
            mid;
        while (high >= low) {
            mid = (high + low) / 2;
            if (a[mid] > number) {
                high = mid - 1;
            } else if (a[mid] < number) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        
    }
}
