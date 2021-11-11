package Week9;
import java.util.HashMap;
import java.util.Scanner;

public class JavaMap {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        HashMap<String, Integer> people = new HashMap<String, Integer>();
        int n = in.nextInt();
        in.nextLine();
        for(int i=0;i<n;i++)
        {
            String name = in.nextLine();
            int phone = in.nextInt();
            in.nextLine();
            people.put(name, phone);
        }
        while(in.hasNext())
        {
            String s=in.nextLine();
            if (people.containsKey(s)) {
                System.out.println(s + "=" + people.get(s));
            } else {
                System.out.println("Not found");
            }
        }
        in.close();
    }
}
