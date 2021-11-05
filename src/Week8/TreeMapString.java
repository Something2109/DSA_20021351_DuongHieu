package Week8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TreeMapString {
    TreeMap<String, Integer> map;
    int longestKey;
    int longestValue;

    TreeMapString() {
        map = new TreeMap<String, Integer>();
        longestKey = 0;
        longestValue = 0;
    }

    public void insertFromFile(String directory) throws FileNotFoundException {
        /** Create scanner. */
        File file = new File(directory + "\\dictionaries.txt");
        Scanner scanner = new Scanner(file);

        /** Input loop. */
        while (scanner.hasNextLine()) {
            /** Read String. */
            String String = scanner.nextLine();
            String = String.toLowerCase().trim();

            /** Add to dictionary. */
            if (!map.containsKey(String)) {
                map.put(String, 0);
            }
            map.replace(String, map.get(String) + 1);
            longestKey = Math.max(longestKey, String.length());
            longestValue = Math.max(longestValue, map.get(String).toString().length());
        }
        scanner.close();
    }

    public void print() {
        int StringNumber = map.size();
        int largestNumber = Math.max("No".length(), Integer.toString(StringNumber).length());
        int longestEnChar = Math.max("String".length(), longestKey);
        int largestRepeat = Math.max("Repeat".length(), longestValue);

        /** Format output string. */
        String format;
        format = "%" + largestNumber + "s | ";
        format += "%" + longestEnChar + "s | ";
        format += "%" + largestRepeat + "s |\n";

        /** Print output as formatted string. */
        System.out.printf(format, "No", "String", "Repeat");
        int index = 1;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.printf(format, index, entry.getKey(), entry.getValue());
            index++;
       }
    }

    public static void main(String[] args) {
        TreeMapString map = new TreeMapString();
        try {
            map.insertFromFile(".\\src\\Week8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        map.print();
    }
}
