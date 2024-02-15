import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last = null;

    private class Node {
        Item item;
        Node next;

        public Node(Item i, Node n) {
            item = i;
            next = n;
        }
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        int cnt = 0;
        for (Node cur = first; cur.next != null; cur = cur.next, cnt++) ;
        return cnt;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(item, first);
        first = newNode;
        if (last == null) {
            last = newNode;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(item, null);
        if (last != null) {
            last.next = newNode;
        }
        last = newNode;
        if (first == null) {
            first = newNode;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        Item res = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        }
        return res;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        Item res = last.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            Node cur = first;
            for (; cur.next != last; cur = cur.next) ;
            cur.next = null;
            last = cur;
        }
        return res;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(1);
        d.addLast(3);
        d.addFirst(2);
        d.removeLast();
        for (int item:d) {
            System.out.println(item);
        }
    }
}
