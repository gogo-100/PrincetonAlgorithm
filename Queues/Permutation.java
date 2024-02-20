import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

    public static void main(String[] args) {
        // In in = new In("permutation5.txt");
        // int k = 3;
        int k = Integer.parseInt(args[0]);
        int cnt = 0;
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            cnt++;
            int test = StdRandom.uniformInt(cnt);
            String s = StdIn.readString();
            if (test < k && randomizedQueue.size() >= k) {
                randomizedQueue.dequeue();
                randomizedQueue.enqueue(s);
            }
            else if (test < k) {
                randomizedQueue.enqueue(s);
            }
        }
        for (String item: randomizedQueue) {
            System.out.println(item);
        }

    }
}
