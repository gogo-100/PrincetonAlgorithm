import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items = (Item[]) new Object[1];
    private int N = 0;

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < items.length; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (items.length == N) {
            resize(items.length * 2);
        }
        items[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniformInt(N);
        Item res = items[index];
        for (int i = index; i < N - 1; i++) {
            items[i] = items[i + 1];
        }
        items[--N] = null;
        if (N * 4 < items.length) {
            resize(items.length / 2);
        }
        return res;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniformInt(N);

        return items[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        Item[] lst = (Item[]) new Object[N];
        for (int i = 0; i < N; i++) {
            lst[i] = items[i];
        }
        StdRandom.shuffle(lst);
        return new ListIterator(lst);
    }

    private class ListIterator implements Iterator<Item> {
        private int index = 0;
        private Item[] lst = null;

        ListIterator(Item[] lst){
            this.lst = lst;
        }

        public boolean hasNext() {
            return index != N;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
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
        q.enqueue(3);
        q.enqueue(2);
        System.out.println(q.dequeue());
        for (int item: q) {
            System.out.println(item);
        }
    }

}
