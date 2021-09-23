import edu.princeton.cs.algs4.*;
import java.util.ArrayList;

public class UFClient2 {
    public static void main(String[] args) { 
        int N = StdIn.readInt(); 
        int count = 0;
        UF uf = new UF(N); 
        ArrayList<Integer> root = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            root.add(i);
        }
        while (root.size() > 1 && !StdIn.isEmpty()) { 
           int p = StdIn.readInt(); 
           int q = StdIn.readInt(); 
           count++;
           if (!uf.connected(p, q)) { 
                int minusRoot = uf.find(p) + uf.find(q);
                uf.union(p, q);
                root.remove(root.indexOf(minusRoot - uf.find(p)));
                StdOut.println(count + " (" + p + " " + q + ")" + " " + root.size()); 
           } 
        } 
        if (root.size() > 1) {
            System.out.println("FAILED");
        }
        else {
            System.out.println("SUCCESSFUL AT ATTEMP " + count);
        }
    }
}
