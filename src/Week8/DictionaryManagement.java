import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    /** Record the longest String string of both language to print on commandline. */
    private ArrayList<Integer> repeat;
    private int longestEnChar;
    private int longestRepeat;

    public DictionaryManagement() {
        repeat = new ArrayList<Integer>();
        longestEnChar = 0;
    }

    public int getLongestRepeat() {
        return longestRepeat;
    }

    public int getLongestEnChar() {
        return longestEnChar;
    }

    public int getRepeatNumber(int index) {
        return repeat.get(index);
    }

    /** Ask if you want to continue input work. */
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
            String StringTarget = scanner.nextLine();
            StringTarget = StringTarget.trim().toLowerCase();

            /** Add String to dictionary. */
            String newString = StringTarget;
            addString(newString);
            longestEnChar = Math.max(newString.length(), longestEnChar);

            continueInput = confirm(scanner);

            System.out.println();
        }
        scanner.close();
    }

    /**
     * Insert Strings from a file.
     * @param directory the file directory
     * @throws FileNotFoundException if file not found
     */
    public void insertFromFile(String directory) throws FileNotFoundException {
        /** Create scanner. */
        File file = new File(directory + "\\dictionaries.txt");
        Scanner scanner = new Scanner(file);

        /** Input loop. */
        while (scanner.hasNextLine()) {
            /** Read String. */
            String String = scanner.nextLine();

            /** Add to dictionary. */
            int index = findString(String);
            if (index == -1) {
                index = addString(String);
                repeat.add(index, 0);
            }
            repeat.set(index, repeat.get(index) + 1);
            longestRepeat = Math.max(longestRepeat, repeat.get(index).toString().length());
            longestEnChar = Math.max(String.length(), longestEnChar);
        }
        scanner.close();
    }

    /**
     * Export to a file.
     * @param directory the file directory
     */
    public void dictionaryExportToFile(String directory) {
        try {
            PrintWriter file = new PrintWriter(directory + "\\dictionaries.txt");
            for (int i = 0; i < getNumberOfString(); i++) {
                file.println(getString(i));
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /** Find the exact String from input. */
    public void dictionaryLookup() {
        Scanner scanner = new Scanner(System.in);
        boolean continueInput = true;

        /** Input loop. */
        while (continueInput) {
            /** Read English Strings. */
            System.out.print("English String: ");
            String StringTarget = scanner.nextLine();
            StringTarget = StringTarget.trim().toLowerCase();
            int index = findString(StringTarget);

            /** Print out the String. */
            if (index > -1) {
                String StringExplain = getString(index);
                System.out.println(StringExplain);
            } else {
                System.out.println("Cannot find String.");
            }

            continueInput = confirm(scanner);

            System.out.println();
        }
        scanner.close();
    }

    /**
     * Search the String range containing all the Strings satisfied the input string.
     * @param searchStr The input String to search
     * @param oldBound The two boundary of the range to search
     * @return The array with two integer representing the upper and lower bound.
     */
    public int[] dictionarySearch(String searchStr, int[] oldBound) {
        int lo = oldBound[0];
        int hi = oldBound[1];
        
        /** Find the range of Strings satisfied the string. */
        int mid;

        /** binary search the char at the index. */
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            String queryString = getString(mid);

            /** Get the comparable string. */
            int index = Math.min(queryString.length(), searchStr.length());
            queryString = queryString.substring(0, index);

            /** Check if querying String is equal the search string. */
            if (queryString.equals(searchStr)) {

                /** if the string has the satisfied searchString. */
                
                int[] bound = queryRangeOfString(searchStr, mid, lo, hi);
                lo = bound[0];
                hi = bound[1];

                /** change to search the next index char. */
                break;

            } else {

                /** if the string doesn't have the satisfied index char. */
                if (queryString.compareTo(searchStr) < 0) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }

            }

        }

        int[] bound = new int[2];
        bound[0] = lo;
        bound[1] = hi;

        return bound;
    }

    /**
     * Find the range of Strings satisfied the index char of the search string.
     * @param mid The place in StringList that the String satisfied searchStr was found.
     * @param lo The lower bound of the querying range.
     * @param hi The upper bound of the querying range.
     * @param searchStr The search string.
     * @return The array with two integer representing the new upper (0) and lower (1) bound.
     */
    private int[] queryRangeOfString(String searchStr, int mid, int lo, int hi) {
        String queryString;
        int[] bound = new int[2];
        bound[0] = mid + 1;
        bound[1] = mid - 1;
        
        /** Scale the lower bound of satisfied String range. */
        do {
            bound[0]--;
            if (bound[0] == lo) {
                break;
            }
            queryString = getString(bound[0] - 1);
            int index = Math.min(queryString.length(), searchStr.length());
            queryString = queryString.substring(0, index);
            
        } while (queryString.equals(searchStr));

        /** Scale the upper bound of satisfied String range. */
        do {
            bound[1]++;
            if (bound[1] == hi) {
                break;
            }
            queryString = getString(bound[1] + 1);
            int index = Math.min(queryString.length(), searchStr.length());
            queryString = queryString.substring(0, index);

        } while (queryString.equals(searchStr));

        
        return bound;
    }

    /**
     * Search the String range containing all the Strings satisfied the input string.
     * @param searchStr The input String to search.
     * @param oldBound The two boundary of the range to search.
     * @param index The index to query the char.
     * @return The array with two integer representing the upper and lower bound.
     * @deprecated
     */
    public int[] dictionarySearch(String searchStr, int[] oldBound, int index) {
        int lo = oldBound[0];
        int hi = oldBound[1];
        
        /** Find the range of Strings satisfied the string. */
        while (index < searchStr.length() && lo <= hi) {
            int mid;

            /** binary search the char at the index. */
            while (lo <= hi) {
                mid = (lo + hi) / 2;
                String queryString = getString(mid);

                /** Check if querying String is long enough to query. */
                if (index < queryString.length()) {

                    /** Check if querying String has the same char at index as the str. */
                    if (queryString.charAt(index) == searchStr.charAt(index)) {

                        /** if the string has the satisfied index char. */
                        
                        int[] bound = queryRangeOfString(searchStr, index, mid, lo, hi);
                        lo = bound[0];
                        hi = bound[1];

                        /** change to search the next index char. */
                        index++;
                        break;

                    } else {

                        /** if the string doesn't have the satisfied index char. */
                        if (queryString.charAt(index) < searchStr.charAt(index)) {
                            lo = mid + 1;
                        } else {
                            hi = mid - 1;
                        }

                    }

                } else {
                    lo = mid + 1;
                }
            }
        }

        int[] bound = new int[2];
        bound[0] = lo;
        bound[1] = hi;

        return bound;
    }

    /**
     * Find the range of Strings satisfied the index char of the search string.
     * @param searchStr The search string.
     * @param index The index to query the char.
     * @param mid The place in StringList that the String satisfied searchStr was found.
     * @param lo The lower bound of the querying range.
     * @param hi The upper bound of the querying range.
     * @return The array with two integer representing the new upper (0) and lower (1) bound.
     * @deprecated
     */
    private int[] queryRangeOfString(String searchStr, int index, int mid, int lo, int hi) {
        String queryString;
        int[] bound = new int[2];
        bound[0] = mid + 1;
        bound[1] = mid - 1;
        
        /** Scale the lower bound of satisfied String range. */
        do {
            bound[0]--;
            if (bound[0] == lo) {
                break;
            }
            queryString = getString(bound[0] - 1);
            if (index >= queryString.length()) {
                break;
            }
        } while (queryString.charAt(index) == searchStr.charAt(index));

        /** Scale the upper bound of satisfied String range. */
        do {
            bound[1]++;
            if (bound[1] == hi) {
                break;
            }
            queryString = getString(bound[1] + 1);
            if (index >= queryString.length()) {
                break;
            }
        } while (queryString.charAt(index) == searchStr.charAt(index));

        
        return bound;
    }
}
