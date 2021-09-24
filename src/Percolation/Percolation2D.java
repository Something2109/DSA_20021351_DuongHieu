package Percolation;
import edu.princeton.cs.algs4.*;

public class Percolation2D {
    int field, side;
    UF uf;
    boolean[] tileStatus;
    int tileCount;

    Percolation2D(int n) {
        side = n;
        field = n * n + 2;
        uf = new UF(field);
        tileStatus = new boolean[field];
        tileCount = 0;
        StdOut.println("Create field: " + side + ", " + field);
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        double count = 0;
        for (int i = 1; i <= 100; i++) {
            StdOut.println("Attemp " + i +"\n");
            Percolation2D per = new Percolation2D(n);
            count += per.run();
            StdOut.println("\n");
        }
        StdOut.println(count / (n * n * 100));
    }

    public int run() {
        settingUp();
        while (!uf.connected((field - 2), (field - 1))) {
            addTile();
            tileCount ++;
        }
        return tileCount;
    }

    private void settingUp() {
        for (int i = 0; i < side; i++) {
            Tile2D tile = new Tile2D(0, i, side);
            uf.union(field - 2, tile.getTileID());
            Tile2D tile1 = new Tile2D(side - 2, i , side);
            uf.union(field - 1, tile1.getTileID());
        }
        StdOut.println();
    }

    private void addTile() {
        Tile2D tile;
        do  {
            tile = new Tile2D(side);
        } 
        while (tileStatus[tile.getTileID()]);
        tileStatus[tile.getTileID()] = true;
        StdOut.println("Open tile: " + tile.toString());
        connectTile(tile);
        StdOut.println();
    }

    private void connectTile(Tile2D tile) {
        Tile2D nextTile = new Tile2D(tile, -1, 0, side);
        unionTile(tile, nextTile);
        nextTile.setFromTile(tile, 1, 0, side);
        unionTile(tile, nextTile);
        nextTile.setFromTile(tile, 0, -1, side);
        unionTile(tile, nextTile);
        nextTile.setFromTile(tile, 0, 1, side);
        unionTile(tile, nextTile);
    }

    private void unionTile(Tile2D tile, Tile2D nextTile) {
        if (nextTile.valid(side) && tileStatus[nextTile.getTileID()]) {
            uf.union(tile.getTileID(), nextTile.getTileID());
            StdOut.println("Connect tile: " + tile.toString() + " " + nextTile.toString());
        }
    }
}