package Week9;
import java.util.HashSet;
import java.util.Scanner;

public class HashSetJava {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        HashSet<String> set = new HashSet<>();
        
        
        for (int i = 0; i < t; i++) {
            String str = s.next() + " " + s.next();
            if (!set.contains(str)) {
                set.add(str);
            }
            System.out.println(set.size());
        }

        s.close();
    }
}
