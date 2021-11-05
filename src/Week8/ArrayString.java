package Week8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayString {
    private ArrayList<String> stringList;
    private ArrayList<Integer> valueList;
    private int longestKey;
    private int longestValue;

    ArrayString() {
        stringList = new ArrayList<String>();
        valueList = new ArrayList<Integer>();
        longestKey = 0;
        longestValue = 0;
    }
    
    public int addString(String newString) {
        int index;
        boolean add = true;
        if (stringList.size() == 0) {
            /** When String list has no String. */
            index = 0;
        } else if (newString.compareTo(stringList.get(0)) < 0) {
            /** When the String target is smaller than the first String of the list. */
            index = 0;
        } else if (newString.compareTo(stringList.get(0)) == 0) {
            /** When the String target is equal the first String of the list. */
            index = 0;
            add = false;
        } else if (stringList.size() == 1) {
            /** 
             * When String list has 1 String and
             * the String target is larger than the first String of the list.
             */
            index = 1;
        } else {
            /** When String list has more than 1 String. */
            int lo = 0;
            int hi = stringList.size() - 1;
            int mid = (lo + hi) / 2;
            /** Binary search the String. */
            while (lo <= hi) {
                int compare = newString.compareTo(stringList.get(mid));
                if (compare < 0) {
                    hi = mid - 1;
                    mid = (lo + hi) / 2;
                } else if (compare > 0) {
                    lo = mid + 1;
                    mid = (lo + hi) / 2;
                } else if (compare == 0) {
                    break;
                }
            }
            if (stringList.get(mid).compareTo(newString) == 0) {
                index = mid;
                add = false;
            } else {
                index = mid + 1;
            }
        }
        if (add) {
            stringList.add(index, newString);
            valueList.add(index, 0);
        }
        return index;
    }

    public void insertFromFile(String directory) throws FileNotFoundException {
        /** Create scanner. */
        File file = new File(directory + "\\dictionaries.txt");
        Scanner scanner = new Scanner(file);

        /** Input loop. */
        while (scanner.hasNextLine()) {
            /** Read String. */
            String String = scanner.nextLine();

            /** Add to dictionary. */
            int index = addString(String);
            // if (index == -1) {
            //     index = addString(String);
            //     valueList.add(index, 0);
            // }
            valueList.set(index, valueList.get(index) + 1);
            longestValue = Math.max(longestValue, valueList.get(index).toString().length());
            longestKey = Math.max(String.length(), longestKey);
        }
        scanner.close();
    }

    public static boolean confirm(Scanner scanner) {
        boolean confirm = true;
        boolean continueInput = true;
        do {
            System.out.print("Continue (y/n): ");
            String check = scanner.nextLine().trim();
            if (check.equals("n")) {
                confirm = false;
                continueInput = false;
            } else if (check.equals("y")) {
                continueInput = false;
            } else {
                System.out.println("Cannot understand input.");
            }
        } while (continueInput);
        return confirm;
    }

    /** Insert Strings from command line. */
    public void insertFromCommandline() {
        /** Create scanner. */
        Scanner scanner = new Scanner(System.in);
        boolean continueInput = true;

        /** Input loop. */
        while (continueInput) {
            
            /** Read English Strings. */
            System.out.print("English String: ");
            String String = scanner.nextLine();
            String = String.trim().toLowerCase();

            /** Add String to dictionary. */
            int index = addString(String);
            valueList.set(index, valueList.get(index) + 1);
            longestValue = Math.max(longestValue, valueList.get(index).toString().length());
            longestKey = Math.max(String.length(), longestKey);
            showAllStrings();

            continueInput = confirm(scanner);

            System.out.println();
        }
        scanner.close();
    }

    public void showAllStrings() {
        int StringNumber = stringList.size();
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
        for (int i = 0; i < StringNumber; i++) {
            System.out.printf(format, (i + 1), stringList.get(i), valueList.get(i));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayString manager = new ArrayString();
        String fileDirectory = ".\\src\\Week8";
        manager.insertFromFile(fileDirectory);
        manager.showAllStrings();
        manager.insertFromCommandline();
    }
}
