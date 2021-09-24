import edu.princeton.cs.algs4.*;

public class ThreeSums {
    public static void main(String[] args) {
        int count = 0;
        String path = "C:\\Users\\Binh\\Downloads\\algs4-data\\" + "4Kints.txt";
        In in = new In(path);
        count = arrayRead(in);
        StdOut.println("Total sums: " + count);
    }

    private static int arrayRead(In in) {
        int count = 0;
        int[] number = in.readAllInts();
        StdArrayIO.print(number);
        for (int i = 0; i < number.length - 2; i++) {
            for (int j = i + 1; j < number.length - 1; j++) {
                for (int k = j + 1; k < number.length; k++) {
                    if (number[i] + number[j] + number[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}