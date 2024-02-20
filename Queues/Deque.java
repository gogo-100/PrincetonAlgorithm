import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    private class Node {
        Item item;
        Node pre;
        Node next;

        public Node(Item i, Node pre, Node next) {
            item = i;
            this.next = next;
            this.pre = pre;
        }
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(item, null,first);
        if(first != null){
            first.pre = newNode;
        }
        first = newNode;
        size++;
        if (last == null) {
            last = newNode;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(item,last, null);
        if (last != null) {
            last.next = newNode;
        }
        newNode.pre = last;
        last = newNode;
        size++;
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
        size --;
        if (first == null) {
            last = null;
        }
        else{
            first.pre = null;
        }
        return res;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        Item res = last.item;
        last = last.pre;
        if(last == null){
            first = null;
        }
        else{
            last.next = null;
        }
        size --;
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
