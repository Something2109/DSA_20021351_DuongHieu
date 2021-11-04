import java.util.ArrayList;

public class Dictionary {
    private ArrayList<String> StringList;
    
    Dictionary() {
        StringList = new ArrayList<String>();
    }

    /** Get the String in the index number. */
    public String getString(int index) {
        return StringList.get(index);
    }

    /** Return the number of Strings in the dictionary */
    public int getNumberOfString() {
        return StringList.size();
    }

    /**
     * Add new String to the dictionary.
     * @param newString String to add
     */
    public int addString(String newString) {
        int index;
        if (StringList.size() == 0) {
            /** When String list has no String. */
            index = 0;
        } else if (newString.compareTo(StringList.get(0)) < 0) {
            /** When the String target is smaller than the first String of the list. */
            index = 0;
        } else if (StringList.size() == 1) {
            /** 
             * When String list has 1 String and
             * the String target is larger than the first String of the list.
             */
            index = 1;
        } else {
            /** When String list has more than 1 String. */
            int lo = 0;
            int hi = StringList.size() - 1;
            int mid = (lo + hi) / 2;
            /** Binary search the String. */
            while (lo <= hi) {
                int compare = newString.compareTo(StringList.get(mid));
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
            if (StringList.get(mid).compareTo(newString) == 0) {
                index = -1;
            } else {
                index = mid + 1;
            }
        }
        if (index > -1) {
            StringList.add(index, newString);
        }
        return index;
    }

    /**
     * Remove the String satisfied the given string.
     * @param StringTarget
     * @return The index of the deleted String.
     */
    public int removeString(String StringTarget) {
        int lo = 0;
        int hi = StringList.size() - 1;
        int index = -1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            String midStr = StringList.get(mid);
            int compare = StringTarget.compareTo(midStr);
            if(compare < 0) {
                hi = mid - 1;
            } else if (compare > 0) {
                lo = mid + 1;
            } else {
                index = mid;
                StringList.remove(index);
                break;
            }
        }
        return index; 
    }

    /**
     * Find the String in the dictionary with the given target.
     * @param StringTarget String to find
     * @return the explanation of the String.
     */
    public int findString(String StringTarget) {
        int lo = 0;
        int hi = StringList.size() - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            String midStr = StringList.get(mid);
            int compare = StringTarget.compareTo(midStr);
            if(compare < 0) {
                    hi = mid - 1;
            } else if (compare > 0) {
                    lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
