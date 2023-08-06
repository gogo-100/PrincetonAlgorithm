import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int index = 1;
        String champion = "gogo";
        while (!StdIn.isEmpty()) {
            String temp = StdIn.readString();
            if (StdRandom.bernoulli(1.0/index)) {
                champion = temp;
            }
            index++;
        }
        System.out.println(champion);
    }
}
