
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF unionFind;
    final int FIRST_LAYER;
    final int LAST_LAYER;
    final int N;
    int[] x4neighbor = new int[]{0, 0, 1, -1};
    int[] y4neighbor = new int[]{1, -1, 0, 0};
    boolean [][] opens;

    // creates n-by-n grid, with all sites initially blocked
    // where (1, 1) is the upper-left site  (n,n) lower-right
    //By convention, the row and column indices are integers between 1 and n
    public Percolation(int n){
        if (n < 0){
            throw new IllegalArgumentException("constructor n ≤ 0");
        }
        N = n;
        //表示虚拟层：一个在最上层，一个在最下层
        FIRST_LAYER = 0;
        LAST_LAYER = n * n + 1;
        unionFind = new WeightedQuickUnionUF(n * n + 2);
        opens = new boolean[N+1][N+1];
    }

    public int getIndex(int row, int col){
        return (row - 1) * N + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if(!checkLegalPosition(row, col)){
            throw new IllegalArgumentException(String.format("row, col is illegal: %d %d", row, col));
        }
        if(!isOpen(row,col)){
            opens[row][col] = true;
            union4neighbor(row,col);
            if(row == 1){
                unionFind.union(getIndex(row,col), FIRST_LAYER);
            }
            if(row == N){
                unionFind.union(getIndex(row,col), LAST_LAYER);
            }
        }
    }

    // unions 4-neighbor
    public void union4neighbor(int row, int col){
        for (int i = 0; i < 4; i++) {
            int row2 = row + x4neighbor[i];
            int col2 = col + y4neighbor[i];
            if(checkLegalPosition(row2,col2) && isOpen(row2,col2)){
                unionFind.union(getIndex(row,col),getIndex(row2,col2));
            }
        }
    }

    //check if is legal position
    public boolean checkLegalPosition(int row, int col){
        return row * col > 0 && row <= N && col <= N;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(!checkLegalPosition(row, col)){
            throw new IllegalArgumentException(String.format("row, col is illegal: %d %d", row, col));
        }
        return opens[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if(!checkLegalPosition(row, col)){
            throw new IllegalArgumentException(String.format("row, col is illegal: %d %d", row, col));
        }
        return unionFind.find(FIRST_LAYER) == unionFind.find(getIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return unionFind.count();
    }

    // does the system percolate?
    public boolean percolates(){
        return unionFind.find(FIRST_LAYER) == unionFind.find(LAST_LAYER);
    }
}