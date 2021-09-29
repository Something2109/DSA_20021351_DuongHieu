import edu.princeton.cs.algs4.*;
// import java.util.ArrayList;

public class TwoSums {
    public static void main(String[] args) {
        int count = 0;
        String path = "C:\\Users\\Binh\\Downloads\\algs4-data\\" + "8Kints.txt";
        In in = new In(path);
        count = arrayRead(in);
        // count = arrayListRead(in);
        StdOut.println("Total sums: " + count);
    }

    private static int arrayRead(In in) {
        int count = 0;
        int[] number = in.readAllInts();
        StdArrayIO.print(number);
        for (int i = 0; i < number.length - 1; i++) {
            for (int j = i + 1; j < number.length; j++) {
                if (number[i] + number[j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    // private static int arrayListRead(In in) {
    //     int count = 0;
    //     ArrayList<Integer> arr = new ArrayList<Integer>();
    //     while(!in.isEmpty()) {
    //         int num = in.readInt();
    //         if (arr.contains(0 - num)) {
    //             count++;
    //         }
    //         if (!arr.contains(num)) {
    //             arr.add(num);
    //             StdOut.println(num);
    //         }
    //     }
    //     return count;
    // }
}
