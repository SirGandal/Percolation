import edu.princeton.cs.algs4.QuickUnionUF;

public class PercolationQF {

  private int[][] directions = new int[][] { { 1, 0 }, // BOTTOM
      { -1, 0 }, // TOP
      { 0, -1 }, // LEFT
      { 0, 1 } // RIGHT
  };

  private int size;
  private int[] grid;

  // Main union find data structure used to determine if the system percolates
  private QuickUnionUF ufMain;

  // Support union find data structure used to discover if a site is full or not
  // and avoid backwash.
  private QuickUnionUF ufSupport;

  public PercolationQF(int n) {

    if (n <= 0) {
      throw new java.lang.IllegalArgumentException();
    }

    this.size = n;

    // add two virtual sites, one connected to all the elements of the top row,
    // and one connected to all the ones at the bottom row.
    ufMain = new QuickUnionUF((n * n) + 2);

    // add one virtual site connected to all the elements of the top row.
    ufSupport = new QuickUnionUF((n * n) + 1);

    // connect top row sites to virtual top site
    for (int i = 1; i < n + 1; i++) {
      ufMain.union(i, 0);
      ufSupport.union(i, 0);
    }

    // connect bottom row sites to virtual bottom site
    for (int i = n * n; i > n * n - n; i--) {
      ufMain.union(i, (n * n) + 1);
    }

    // create n-by-n grid, with all sites blocked
    // No need to keep a multidimensional array
    grid = new int[n * n];

    // Initialize all to full
    for (int i = 0; i < n * n; i++) {
      grid[i] = 0;
    }
  }

  // open site (row i, column j) if it is not open already
  public void open(int i, int j) {
    if (!isValidCoordinate(i, j)) {
      throw new java.lang.IndexOutOfBoundsException();
    }

    int tmpi = i;
    int tmpj = j;

    if (isBlocked(i, j)) {
      // it is indeed blocked, let's open it
      grid[getIndex(i, j)] = 1;

      for (int d = 0; d < directions.length; d++) {
        i = tmpi + directions[d][0];
        j = tmpj + directions[d][1];

        // if a site in one of the allowed directions is open we can perform a
        // connection
        if (isValidCoordinate(i, j) && isOpen(i, j)) {
          // shift by one because of the virtual top site
          int siteA = getIndex(i, j) + 1;
          int siteB = getIndex(tmpi, tmpj) + 1;
          ufMain.union(siteA, siteB);
          ufSupport.union(siteA, siteB);
        }
      }

    }
  }

  // is site (row i, column j) open?
  public boolean isOpen(int i, int j) {
    if (isValidCoordinate(i, j)) {
      return grid[getIndex(i, j)] == 1;
    }
    throw new java.lang.IndexOutOfBoundsException();
  }

  // is site (row i, column j) full?
  public boolean isFull(int i, int j) {
    if (isValidCoordinate(i, j)) {
      if (isOpen(i, j)) {

        // index is shifted by one due to the virtual top site
        int index = getIndex(i, j) + 1;

        if (index < size) {
          // by definition an open site at the first row is full
          return true;
        }

        return ufSupport.connected(index, 0);
      }
    } else {
      throw new java.lang.IndexOutOfBoundsException();
    }

    return false;
  }

  // is site (row i, column j) blocked?
  private boolean isBlocked(int i, int j) {
    if (isValidCoordinate(i, j)) {
      return grid[getIndex(i, j)] == 0;
    }
    return false;
  }

  // does the system percolate?
  public boolean percolates() {
    // we don't use the isFull definition
    int virtualTop = 0;
    int virtualBottom = size * size + 1;

    // corner case: 1 site
    if (virtualBottom == 2) {
      return isOpen(1, 1);
    }

    // corner case: no sites
    if (virtualBottom == 0) {
      return false;
    }

    return ufMain.connected(virtualTop, virtualBottom);
  }

  // get single index from row and column
  private int getIndex(int i, int j) {
    return (i - 1) * size + (j - 1);
  }

  private boolean isValidCoordinate(int i, int j) {
    if (i > 0 && i <= size && j > 0 && j <= size) {
      return true;
    }

    return false;
  }
}