import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement {
    static final String NO = "No";
    static final String EN = "English";
    static final String REPEAT = "Repeat";

    /** Show all Strings in dictionary. */
    void showAllStrings() {
        int StringNumber = getNumberOfString();
        int largestNumber = Math.max(NO.length(), Integer.toString(StringNumber).length());
        int longestEnChar = Math.max(EN.length(), getLongestEnChar());
        int largestRepeat = Math.max(REPEAT.length(), getLongestRepeat());

        /** Format output string. */
        String format;
        format = "%" + largestNumber + "s | ";
        format += "%" + longestEnChar + "s | ";
        format += "%" + largestRepeat + "s |\n";

        /** Print output as formatted string. */
        System.out.printf(format, NO, EN, REPEAT);
        for (int i = 0; i < StringNumber; i++) {
            String String = getString(i);
            System.out.printf(format, (i + 1), String, getRepeatNumber(i));
        }
    }

    /** Search the String satisfied the input string. */
    void dictionarySearcher() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search: ");
        String searchStr = scanner.nextLine().trim().toLowerCase();
        int[] oldBound = new int[2];
        oldBound[0] = 0;
        oldBound[1] = getNumberOfString() - 1;
        int[] bound = dictionarySearch(searchStr, oldBound);
        for (int i = bound[0]; i <= bound[1]; i++) {
            System.out.println(getString(i));
        }
        scanner.close();
    }

    /** Basic dictionary with command line input and show all the input added. */
    static void dictionaryBasic() {
        DictionaryCommandline manager = new DictionaryCommandline();
        manager.insertFromCommandline();
        manager.showAllStrings();
    }

    /** Dictionary with file input, show all the input added and find Strings. */
    static void dictionaryAdvanced() throws FileNotFoundException {
        DictionaryCommandline manager = new DictionaryCommandline();
        String fileDirectory = ".";
        manager.insertFromFile(fileDirectory);
        manager.showAllStrings();
        manager.dictionarySearcher();
        manager.dictionaryExportToFile(fileDirectory);
    }

    public static void main(String[] args) throws FileNotFoundException {
        dictionaryAdvanced();
    }
}
