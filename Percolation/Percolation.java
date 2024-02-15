import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF unionFind;
    // private WeightedQuickUnionUF unionFindWithoutLastLayer;  // for preventing backwash
    private int n, firstLayer, lastLayer, openCnt = 0;
    private int[] x4neighbor = new int[]{0, 0, 1, -1};
    private int[] y4neighbor = new int[]{1, -1, 0, 0};
    private boolean [][] opens;

    // creates n-by-n grid, with all sites initially blocked
    // where (1, 1) is the upper-left site  (n,n) lower-right
    // By convention, the row and column indices are integers between 1 and n
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("constructor n ≤ 0");
        }
        this.n = n;
        firstLayer = 0;   // virtual layer
        lastLayer = n * n + 1; // virtual layer
        unionFind = new WeightedQuickUnionUF(n * n + 2);
        // unionFindWithoutLastLayer = new WeightedQuickUnionUF(n * n + 2);
        opens = new boolean[n+1][n+1];
    }

    private int getIndex(int row, int col) {
        return (row - 1) * n + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!checkLegalPosition(row, col)) {
            throw new IllegalArgumentException(String.format("row, col is illegal: %d %d", row, col));
        }
        if (!isOpen(row, col)) {
            opens[row][col] = true;
            union4neighbor(row, col);
            openCnt++;
            if (row == 1) {
                unionFind.union(getIndex(row, col), firstLayer);
                // unionFindWithoutLastLayer.union(getIndex(row, col), firstLayer);
            }
            if (row == n) {
                // unionFind.union(getIndex(row, col), lastLayer);
            }
        }
    }

    // unions 4-neighbor
    private void union4neighbor(int row, int col) {
        for (int i = 0; i < x4neighbor.length; i++) {
            int row2 = row + x4neighbor[i];
            int col2 = col + y4neighbor[i];
            if (checkLegalPosition(row2, col2) && isOpen(row2, col2)) {
                unionFind.union(getIndex(row, col), getIndex(row2, col2));
                //unionFindWithoutLastLayer.union(getIndex(row, col), getIndex(row2, col2));
            }
        }
    }

    // check if is legal position
    private boolean checkLegalPosition(int row, int col) {
        return row * col > 0 && row <= n && col <= n;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!checkLegalPosition(row, col)) {
            throw new IllegalArgumentException(String.format("row, col is illegal: %d %d", row, col));
        }
        return opens[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!checkLegalPosition(row, col)) {
            throw new IllegalArgumentException(String.format("row, col is illegal: %d %d", row, col));
        }
        //return unionFindWithoutLastLayer.find(firstLayer) == unionFindWithoutLastLayer.find(getIndex(row, col));
        return unionFind.find(firstLayer) == unionFind.find(getIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCnt;
    }

    // does the system percolate?
    public boolean percolates() {
        return unionFind.find(firstLayer) == unionFind.find(lastLayer);
    }
}