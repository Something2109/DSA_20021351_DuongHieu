package Coursera.DequeAndRandomizedQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first;
    private int n;

    private class Node {
        private final Item data;
        private Node next;

        Node(Item item) {
            this.data = item;
            this.next = null;
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (n != 0) {
            int deq = StdRandom.uniform(n);
            if (deq == 0) {
                Node store = first;
                first = new Node(item);
                first.next = store;
            } else {
                Node query = first;
                for (int i = 0; i < deq - 1; i++) {
                    query = query.next;
                }
                Node store = query.next;
                query.next = new Node(item);
                query.next.next = store;
            }
        } else {
            first = new Node(item);
        }
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        int deq = StdRandom.uniform(n);
        Item item;
        if (deq == 0) {
            item = first.data;
            first = first.next;
        } else {
            Node remove = first;
            for (int i = 0; i < deq - 1; i++) {
                remove = remove.next;
            }
            item = remove.next.data;
            remove.next = remove.next.next;
        }
        n--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        int deq = StdRandom.uniform(n);
        Item item;
        if (deq == 0) {
            item = first.data;
        } else {
            Node remove = first;
            for (int i = 0; i < deq - 1; i++) {
                remove = remove.next;
            }
            item = remove.next.data;
        }
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ItemIterator();
    }

    private class ItemIterator implements Iterator<Item> {

        boolean[] checked = new boolean[n];
        int itemCheck = n;

        @Override
        public boolean hasNext() {
            return itemCheck != 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int index;
            do {
                index = StdRandom.uniform(n);
            } while (checked[index]);
            Node remove = first;
            for (int i = 0; i < index; i++) {
                remove = remove.next;
            }
            Item item = remove.data;
            checked[index] = true;
            itemCheck--;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 1; i <= 10; i++) {
            rq.enqueue(i);
        }
        for (Integer n : rq) {
            System.out.println(n);
        }
    }

}
