package Week2.UFClient;
import edu.princeton.cs.algs4.*;

public class UFClient2 {
    public static void main(String[] args) { 
        int N = StdIn.readInt(); 
        int count = N;
        UF uf = new UF(N); 
        while (count > 1 && !StdIn.isEmpty()) { 
           int p = StdIn.readInt(); 
           int q = StdIn.readInt(); 
           if (uf.find(p) != uf.find(q)) { 
                uf.union(p, q);
                count --;
                StdOut.println(count + " (" + p + " " + q + ")" + " " + count); 
           } 
        } 
        if (count > 1) {
            StdOut.println("FAILED");
        }
        else {
            StdOut.println("SUCCESSFUL AT ATTEMP ");
        }
    }
}
