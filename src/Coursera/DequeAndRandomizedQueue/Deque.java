package Coursera.DequeAndRandomizedQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private static final int INIT_CAPACITY = 8;
    private Item[] arr;
    private int n;
    private int first;
    private int last;

    // construct an empty deque
    public Deque() {
        arr = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 1;
        last = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    private void resize(int capacity) {
        if (capacity > INIT_CAPACITY) {
            Item[] newArr = (Item[]) new Object[capacity];
            for (int i = 0; i < n; i++) {
                newArr[i] = arr[(first + i) % arr.length];
            }
            arr = newArr;
            first = 0;
            last = n - 1;
        }
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (n == arr.length) {
            resize(2 * arr.length);
        }
        first = (first + arr.length - 1) % arr.length;
        arr[first] = item;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (n == arr.length) {
            resize(2 * arr.length);
        }
        last = (last + arr.length + 1) % arr.length;
        arr[last] = item;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
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
        if (n == 0) {
            throw new NoSuchElementException();
        }
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

        int index = first;

        @Override
        public boolean hasNext() {
            boolean result = !isEmpty();
            if (result) { 
                if (first <= last) {
                    result = (index >= first && index <= last);
                } else {
                    result = (index >= first || index <= last);
                }
            }
            return result;
        }
    
        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = arr[index];
            index = (index + 1 + arr.length) % arr.length;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<Integer>();
        dq.addFirst(123);
        for (Integer n : dq) {
            System.out.println(n);
        }
    }
}