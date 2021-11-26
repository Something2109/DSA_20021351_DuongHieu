package Week11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.TreeMap;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdOut;

public class Week11 {

    // Pairs
    public static int pairs(int k, List<Integer> arr) {
        HashSet<Integer> ht = new HashSet<>();
        int count = 0;
        for (Integer n : arr) {
            if (ht.contains(n - k)) {
                count++;
            }
            if (ht.contains(n + k)) {
                count++;
            }
            ht.add(n);
        }
        return count;
    }

    // Missing numbers (Hashtable)
    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {
        ArrayList<Integer> missing = new ArrayList<>();
        Hashtable<Integer, Integer> ht = new Hashtable<>();
        for (Integer n : arr) {
            if (!ht.containsKey(n)) {
                ht.put(n, 1);
            } else {
                ht.replace(n, ht.get(n) + 1);
            }
        }
        for (Integer n : brr) {
            if (!ht.containsKey(n)) {
                if (!duplicate(missing, n)) {
                    missing.add(n);
                }
            } else {
                ht.replace(n, ht.get(n) - 1);  
            }
            ht.remove(n, 0);
        }
        Collections.sort(missing);
        return missing;
    }

    // Missing numbers (sort)
    public static List<Integer> missingNumbers2(List<Integer> arr, List<Integer> brr) {
        ArrayList<Integer> missing = new ArrayList<>();
        Collections.sort(arr);
        Collections.sort(brr);
        int i = 0;
        for (Integer n : brr) {
            if (i >= arr.size() || !arr.get(i).equals(n)) {
                if (!duplicate(missing, n)) {
                    missing.add(n);  
                }
            } else {
                i++;
            }
        }
        return missing;
    }

    private static boolean duplicate(List<Integer> missing, Integer n) {
        for (Integer m : missing) {
            if (Integer.compare(m, n) == 0) {
                return true;
            }
        }
        return false;
    }


    public static List<Double> runningMedian(List<Integer> a) {
        // Write your code here
        ArrayList<Double> arr = new ArrayList<>();
        TreeMap<Integer, Integer> hs = new TreeMap<>();
        double mean = a.get(0);
        int dupCount = 0;
        arr.add(mean);
        int lo = Math.min(a.get(0), a.get(1));
        int loNo = 1;
        int hi = Math.max(a.get(0), a.get(1));
        arr.add((lo + hi) / 2.0);
        int hiNo = 1;
        hs.put(a.get(0), 1);
        hs.put(a.get(1), 1);
        for (int i = 2; i < a.size(); i++) {
            if (!hs.containsKey(a.get(i))) {
                hs.put(a.get(i), 1);
            } else {
                hs.replace(a.get(i), hs.get(a.get(i)) + 1);
                dupCount++;
            }
            if ((hs.size() + dupCount) % 2 == 1) {
                if (a.get(i) < lo) {
                    mean = lo;
                    if (loNo == 1) {
                        lo = hs.lowerKey(lo);
                        loNo = hs.get(lo);
                    } else {
                        loNo--;
                    }
                } else if (a.get(i) > hi) {
                    mean = hi;
                    if (hiNo == hs.get(hi)) {
                        hi = hs.higherKey(hi);
                        hiNo = 1;
                    } else {
                        hiNo++;
                    }
                } else {
                    mean = a.get(i);
                }
                
            } else {
                if (a.get(i) <= lo) {
                    if (hi == mean) {
                        hiNo--;
                    } else {
                        hi = (int) mean;
                        hiNo = hs.get((int) mean);
                    }
                    if (a.get(i) == lo) {
                        loNo++;
                    }
                } else if (a.get(i) >= hi) {
                    if (lo < mean) {
                        lo = (int) mean;
                        loNo = 1;
                    } else {
                        loNo++;
                    }
                    if (a.get(i) == hi) {
                        hiNo--;
                    }
                } else if (a.get(i) < mean) {
                    lo = a.get(i);
                    loNo = 1;
                    if (hi != mean) {
                        hi = (int) mean;
                        hiNo = hs.get(hi);
                    } else {
                        hiNo--;
                    }
                } else {
                    if (lo == mean) {
                        loNo++;
                    } else {
                        lo = (int) mean;
                        loNo = 1;
                    }
                    hi = a.get(i);
                    hiNo = hs.get(hi);
                }
                mean = (lo + hi) / 2.0;
                
            }
            
            arr.add(mean);
        }
        return arr;
    }

    public static void main(String[] args) {
        In in = new In("In.txt");
        int n = in.readInt();
        ArrayList<Integer> arr = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            arr.add(in.readInt());
        }
        Out out = new Out("Out.txt");
        for (Double a : runningMedian(arr)) {
            out.println(a);
        }
        In result = new In("Result.txt");
        In output = new In("Out.txt");
        for (int i = 0; i < n; i++) {
            if (result.readDouble() != output.readDouble()) {
                StdOut.println(i);
                break;
            }
        }
    }
}