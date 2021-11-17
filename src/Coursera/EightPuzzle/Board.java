import java.util.ArrayList;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Board {

    private final int[] tileNum;
    private final int side;
    private ArrayList<Board> neighbors;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException();
        }
        side = tiles.length;
        tileNum = new int[side * side];
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                int index = i * side + j;
                tileNum[index] = tiles[i][j];
            }
        }
    }

    private Board(Board parent, int[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException();
        }
        side = tiles.length;
        tileNum = new int[side * side];
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                int index = i * side + j;
                tileNum[index] = tiles[i][j];
            }
        }
        createNeighbors(parent);
    }
                                           
    // string representation of this board
    public String toString() {
        StringBuilder output = new StringBuilder(Integer.toString(side));
        for (int i = 0; i < tileNum.length; i++) {
            if (i % side == 0) {
                output.append("\n");
            }
            output.append(String.format(" %d", tileNum[i]));
            
        }
        return output.toString();
    }

    // board dimension n
    public int dimension() {
        return side;
    }

    // number of tiles out of place
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < tileNum.length - 1; i++) {
            int index = (i + 1) % tileNum.length;
            if (tileNum[i] != index) {
                hamming++;
            }
        }
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < tileNum.length; i++) {
            if (tileNum[i] != 0) {
                int dest = tileNum[i] - 1;
                int distance = Math.abs(i % side - dest % side);
                distance += Math.abs(i / side - dest / side);
                manhattan += distance; 
            }
        }
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < tileNum.length - 1; i++) {
            int index = (i + 1) % tileNum.length;
            if (tileNum[i] != index) {
                return false;
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        boolean result = true;
        if (y == null) {
            result = false;
        } else if (y.getClass().equals(this.getClass())) {
            Board that = (Board) y;
            if (this.side == that.side) {
                for (int i = 0; i < this.tileNum.length; i++) {
                    if (this.tileNum[i] != that.tileNum[i]) {
                        result = false;
                        break;
                    }
                }
            } else {
                result = false;
            }
        } else {
            result = false;
        }
        return result;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        if (neighbors == null) {
            createNeighbors();
        } else if (neighbors.size() == 1) {
            addNeighbors();
        }
        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int num0 = 0;
        int num1 = tileNum.length - 1;

        while ((tileNum[num0] == 0 || tileNum[num1] == 0) && num0 <= num1) {
            if (tileNum[num0] == 0) {
                num0++;
            }
            if (tileNum[num1] == 0) {
                num1--;
            }
        }
    
        int[][] tiles = new int[side][side];
        for (int i = 0; i < tileNum.length; i++) {
            int index;
            if (i == num0) {
                index = num1;
                
            } else if (i == num1) {
                index = num0;
                
            } else {
                index = i;
            }
            tiles[index / side][index % side] = tileNum[i];
        }
        return new Board(tiles);
    }

    /** Create neighbor when there's no parent board. */
    private void createNeighbors() {

        neighbors = new ArrayList<>();
        int[][] tiles = new int[side][side];
        int row0 = 0; 
        int col0 = 0;
        for (int i = 0; i < tileNum.length; i++) {
            tiles[i / side][i % side] = tileNum[i];
            if (tileNum[i] == 0) {
                row0 = i / side;
                col0 = i % side;
            }
        }
        if (row0 > 0) {
            tiles[row0][col0] = tiles[row0 - 1][col0];
            tiles[row0 - 1][col0] = 0;
            neighbors.add(new Board(this, tiles));
            tiles[row0 - 1][col0] = tiles[row0][col0];
            tiles[row0][col0] = 0;
        }
        if (row0 < side - 1) {
            tiles[row0][col0] = tiles[row0 + 1][col0];
            tiles[row0 + 1][col0] = 0;
            neighbors.add(new Board(this, tiles));
            tiles[row0 + 1][col0] = tiles[row0][col0];
            tiles[row0][col0] = 0;
        }
        if (col0 > 0) {
            tiles[row0][col0] = tiles[row0][col0 - 1];
            tiles[row0][col0 - 1] = 0;
            neighbors.add(new Board(this, tiles));
            tiles[row0][col0 - 1] = tiles[row0][col0];
            tiles[row0][col0] = 0;
        }
        if (col0 < side - 1) {
            tiles[row0][col0] = tiles[row0][col0 + 1];
            tiles[row0][col0 + 1] = 0;
            neighbors.add(new Board(this, tiles));
            tiles[row0][col0 + 1] = tiles[row0][col0];
            tiles[row0][col0] = 0;
        }
    }

    /** Create neighbor when there's a parent board. */
    private void createNeighbors(Board parent) {

        neighbors = new ArrayList<>();
        neighbors.add(parent);
    }

    /** Add the missing neighbor boards when created with a parent board. */
    private void addNeighbors() {
        int[][] tiles = new int[side][side];
        int row0 = 0; 
        int col0 = 0;
        for (int i = 0; i < tileNum.length; i++) {
            tiles[i / side][i % side] = tileNum[i];
            if (tileNum[i] == 0) {
                row0 = i / side;
                col0 = i % side;
            }
        }
        if (row0 > 0) {
            tiles[row0][col0] = tiles[row0 - 1][col0];
            tiles[row0 - 1][col0] = 0;
            Board newBoard = new Board(this, tiles);
            if (!neighbors.get(neighbors.size() - 1).equals(newBoard)) {
                neighbors.add(0, newBoard);
            }
            tiles[row0 - 1][col0] = tiles[row0][col0];
            tiles[row0][col0] = 0;
        }
        if (row0 < side - 1) {
            tiles[row0][col0] = tiles[row0 + 1][col0];
            tiles[row0 + 1][col0] = 0;
            Board newBoard = new Board(this, tiles);
            if (!neighbors.get(neighbors.size() - 1).equals(newBoard)) {
                neighbors.add(0, newBoard);
            }
            tiles[row0 + 1][col0] = tiles[row0][col0];
            tiles[row0][col0] = 0;
        }
        if (col0 > 0) {
            tiles[row0][col0] = tiles[row0][col0 - 1];
            tiles[row0][col0 - 1] = 0;
            Board newBoard = new Board(this, tiles);
            if (!neighbors.get(neighbors.size() - 1).equals(newBoard)) {
                neighbors.add(0, newBoard);
            }
            tiles[row0][col0 - 1] = tiles[row0][col0];
            tiles[row0][col0] = 0;
        }
        if (col0 < side - 1) {
            tiles[row0][col0] = tiles[row0][col0 + 1];
            tiles[row0][col0 + 1] = 0;
            Board newBoard = new Board(this, tiles);
            if (!neighbors.get(neighbors.size() - 1).equals(newBoard)) {
                neighbors.add(0, newBoard);
            }
            tiles[row0][col0 + 1] = tiles[row0][col0];
            tiles[row0][col0] = 0;
        }
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int side = 3;
        int[] arr = new int[side * side];
        for (int i = 0; i < side * side; i++) {
            arr[i] = i;
        }
        StdRandom.shuffle(arr);
        int[][] tiles = new int[side][side];
        for (int i = 0; i < side * side; i++) {
            tiles[i / side][i % side] = arr[i];
        }
        Board initial = new Board(tiles);
        StdOut.println(initial);
        StdOut.println(initial.manhattan());
        StdOut.println(initial.hamming());
        StdOut.println(initial.isGoal());
        Iterable<Board> neighbor = initial.neighbors();
        for (Board b : neighbor) {
            StdOut.println(b);
            StdOut.println(b.manhattan());
        }
        StdOut.println(initial.twin());
    }
}