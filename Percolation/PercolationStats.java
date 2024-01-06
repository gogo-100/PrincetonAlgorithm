import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] percs;
    private int n, t;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("constructor n ≤ 0 or trials < 0");
        }
        percs = new double[trials];
        this.n = n;
        t = trials;
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            int cnt = 0;
            while (!perc.percolates()) {
                int a = StdRandom.uniformInt(n) + 1;
                int b = StdRandom.uniformInt(n) + 1;
                if (!perc.isOpen(a, b)) {
                    perc.open(a, b);
                    cnt++;
                }
            }
            percs[i] = cnt * 1.0/n/n;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percs);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percs);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(t));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(t));
    }

    // test client (see below)
    public static void main(String[] args) {
        // PercolationStats p = new PercolationStats(20, 10);
        PercolationStats p = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.printf("mean = %f\n", p.mean());
        System.out.printf("stddev = %f\n", p.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]\n", p.confidenceLo(), p.confidenceHi());
    }

}