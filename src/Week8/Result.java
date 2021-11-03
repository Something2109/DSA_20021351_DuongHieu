package Week8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Result {

    /** Palindrome. */
    public static void Palindrome(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String A = scanner.next();
        scanner.close();
        int lo = 0;
        int hi = A.length() - 1;
        while (lo < hi) {
            if (A.charAt(lo) != A.charAt(hi)) {
                System.out.println("No");
                break;
            }
            lo++;
            hi--;
        }
        if (lo >= hi) {
            System.out.println("Yes");
        }
    }

    /** Jesse and Cookies. */
    public static int cookies(int k, List<Integer> A) {
        // Write your code here
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>(A);
            int step = 0;
            while (pq.element().intValue() < k && pq.size() > 1) {
                int a = pq.remove().intValue();
                int b = pq.remove().intValue();
                int c = a + 2 * b;
                pq.add(c);
                step++;
            }
            if (pq.element().intValue() < k) {
                step = -1;
            }
            return step;
        }    

    /** Running Median. */
    public static List<Double> runningMedian(List<Integer> a) {
        // Write your code here
        ArrayList<Double> arr = new ArrayList<Double>();
        PriorityQueue<Integer> minPq = new PriorityQueue<Integer>(Collections.reverseOrder());
        PriorityQueue<Integer> maxPq = new PriorityQueue<Integer>();
        
        arr.add(a.get(0).doubleValue());
        minPq.add(a.get(0));
        
        int i = 1;
        do {
            Integer min = minPq.remove();

            minPq.add(Math.min(min, a.get(i)));
            maxPq.add(Math.max(min, a.get(i)));

            if (maxPq.size() > minPq.size()) {
                minPq.add(maxPq.remove());
            }

            double mean;
            if (minPq.size() == maxPq.size()) {
                mean = minPq.element().intValue() + maxPq.element().intValue();
                mean /= 2;
            } else {
                mean = minPq.element().doubleValue();
            }
            arr.add(mean);           
            i++;
        } while (i < a.size());

        return arr;
    }
}
