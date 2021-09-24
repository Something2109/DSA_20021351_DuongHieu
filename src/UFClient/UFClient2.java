package UFClient;
import edu.princeton.cs.algs4.*;
// import java.util.ArrayList;

public class UFClient2 {
    public static void main(String[] args) { 
        int N = StdIn.readInt(); 
        int count = N;
        UF uf = new UF(N); 
        while (count > 1 && !StdIn.isEmpty()) { 
           int p = StdIn.readInt(); 
           int q = StdIn.readInt(); 
           if (!uf.connected(p, q)) { 
                uf.union(p, q);
                count --;
                StdOut.println(count + " (" + p + " " + q + ")" + " " + count); 
           } 
        } 
        if (count > 1) {
            System.out.println("FAILED");
        }
        else {
            System.out.println("SUCCESSFUL AT ATTEMP ");
        }
    }
}
