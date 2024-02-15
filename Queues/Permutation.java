import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        //In in = new In("permutation5.txt");
        //int k = 3;
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            randomizedQueue.enqueue(s);
        }
        for (String item: randomizedQueue) {
            if (k <= 0) {
                break;
            }
            System.out.println(item);
            k--;
        }

    }
}
