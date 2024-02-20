import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        n = 0;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = items[i];
            items[i] = null;
        }
        items = copy;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (items.length == size()) resize(items.length * 2);
        items[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int index = StdRandom.uniformInt(n);
        Item res = items[index];
        items[index] = items[n-1];
        items[--n] = null;
        if (n!= 0 && n * 4 < items.length) resize(items.length / 2);
        return res;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int index = StdRandom.uniformInt(n);
        return items[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        Item[] lst = (Item[]) new Object[n];
        for (int i = 0; i < n; i++) lst[i] = items[i];
        StdRandom.shuffle(lst);
        return new ListIterator(lst);
    }

    private class ListIterator implements Iterator<Item> {
        private int index = 0;
        private Item[] lst = null;

        ListIterator(Item[] lst) {
            this.lst = lst;
        }

        public boolean hasNext() {
            return index != n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return lst[index++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        q.enqueue(1);
        q.dequeue();
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(4);
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        for (int item: q) {
            System.out.println(item);
        }
    }

}
