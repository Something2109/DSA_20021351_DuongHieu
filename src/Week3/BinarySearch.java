package Week3;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class BinarySearch {
    public static int binarySearch(int[] a, int number) {
        int high = a.length,
            low = 0,
            mid;
        while (high > low) {
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

    public static String balancedSums(List<Integer> arr) {
        // Write your code here
        int sumLeft = 0;
        int sumRight = 0;
        for (int i = 1; i < arr.size(); i++) {
            sumRight = sumRight + arr.get(i);
        }
        int i = 0;
        do {
            if (sumLeft == sumRight) {
                return "YES";
            }
            else {
                sumLeft += arr.get(i);
                sumRight -= arr.get(i + 1);
                i++;
            }
        } while (i < arr.size() - 1);
        return "NO";
    }

    public static List<Integer> closestNumbers(List<Integer> arr) {
        // Write your code here
        Collections.sort(arr);
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

    public static int pairs(int k, List<Integer> arr) {
        // Write your code here
        Collections.sort(arr);
        int count = 0;
        int up = 1;
        int down = 0;
        while (up < arr.size()) {
            int difference = arr.get(up) - arr.get(down);
            if (difference <= k) {
                if (difference == k) {
                    count++;
                }
                up++;
            } else {
                down++;
            }
        }
        return count;
    }

    public static void minimumBribes(List<Integer> q) {
        // Write your code here
        int[] count = new int[q.size()];
        int change = 0;
        ArrayList<Integer> place = new ArrayList<Integer>(q.size());
        for (int i = 0; i < q.size(); i++) {
            count[i] = 0;
            place.add(i);
        }
        for (int i = 0; i < q.size(); i++) {
            int objectInI = q.get(i) - 1;
            if (place.get(objectInI) - i > 2) {
                System.out.println("Too chaotic");
                return;
            } else {  
                while (place.get(objectInI) != i) {
                    int objectInFrontOfI = place.indexOf(place.get(objectInI) - 1);
                    place.set(objectInFrontOfI, place.get(objectInI));
                    place.set(objectInI, place.get(objectInI) - 1);
                    count[objectInI]++;
                    if (count[objectInI] > 2) {
                        System.out.println("Too chaotic");
                        return;
                    }
                    change++;
                }
            }
        }
        System.out.println(change);
    }

    public static void main(String[] args) {
        int a[] = { 123, 34, 5703, 1, 198, 125, 931, 23, 12};
        Arrays.sort(a);
        StdOut.println(Arrays.toString(a));
        StdOut.println(binarySearch(a, 5703));

        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < a.length; i++) {
            arr.add(a[i]);
        }
        StdOut.println(balancedSums(arr));
        StdOut.println(closestNumbers(arr));
        StdOut.println(pairs(2, arr));
        StdOut.println();

        ArrayList<Integer> arr2 = new ArrayList<Integer>();
        arr2.add(2);
        arr2.add(1);
        arr2.add(5);
        arr2.add(3);
        arr2.add(4);
        minimumBribes(arr2);
    }
}
