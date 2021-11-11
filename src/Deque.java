import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 8;

    Item[] arr;
    int n;
    int first;
    int last;

    // construct an empty deque
    public Deque() {
        arr = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 0;
        last = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    private void resize(int capacity) {
        Item[] newArr = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            newArr[i] = arr[(first + i) % arr.length];
        }
        arr = newArr;
        first = 0;
        last = n;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (n == arr.length) {
            resize(2 * arr.length);
        }
        first = (first + arr.length - 1) % arr.length;
        arr[first] = item;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (n == arr.length) {
            resize(2 * arr.length);
        }
        last = (last + arr.length + 1) % arr.length;
        arr[last] = item;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item remove = arr[first];
        arr[first] = null;
        n--;
        first = (first + arr.length + 1) % arr.length;

        if (n == arr.length / 4) {
            resize(arr.length / 2);
        }
        return remove;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item remove = arr[last];
        arr[last] = null;
        n--;
        last = (last + arr.length - 1) % arr.length;
        if (n == arr.length / 4) {
            resize(arr.length / 2);
        }
        return remove;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ItemIterator();
    }

    private class ItemIterator implements Iterator<Item> {

        int n = first;

        @Override
        public boolean hasNext() {
            boolean result; 
            if (first <= last) {
                result = (n >= first && n < last);
            } else {
                result = (n >= first || n < last);
            }
            return result;
        }
    
        @Override
        public Item next() {
            Item item = arr[n];
            n = (n + 1 + arr.length) % arr.length;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<Integer>();
        dq.addFirst(12);
        dq.addLast(123);
        System.out.print(dq.removeLast() + " " + dq.size());
    }
}