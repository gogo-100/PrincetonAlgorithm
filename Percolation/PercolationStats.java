public class PercolationStats {

    private int[] randomNumbers;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 0 || trials < 0){
            throw new IllegalArgumentException("constructor n ≤ 0 or trials < 0");
        }
        for i in
        uniformDouble()
    }

    // sample mean of percolation threshold
    public double mean() {
        //todo
        return 1.00;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        //todo
        return 1.00;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        //todo
        return 1.00;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        //todo
        return 1.00;
    }

    // test client (see below)
    public static void main(String[] args){
        int n = 200;
        int t = 100;
        PercolationStats p = new PercolationStats(n,t);
        System.out.println(p.mean());
        System.out.println(p.stddev());
        System.out.println(p.confidenceLo());
        System.out.println(p.confidenceHi());
    }

}