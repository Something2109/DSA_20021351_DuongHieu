package Week2.Percolation;
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

    public int run() {
        int[] node = edgeSettingUp();
        while (!uf.connected(node[0], node[1])) {
            addTile();
            tileCount ++;
        }
        return tileCount;
    }

    private int[] sideSettingUp() {
        int[] node = new int[2];
        node[0] = field - 2;
        node[1] = field - 1;
        for (int i = 0; i < side; i++) {
            Tile2D tile = new Tile2D(0, i, side);
            uf.union(node[0], tile.getTileID());
            Tile2D tile1 = new Tile2D(side - 2, i , side);
            uf.union(node[1], tile1.getTileID());
        }
        StdOut.println();
        return node;
    }

    private int[] edgeSettingUp() {
        int[] node = new int[2];
        Tile2D tile = new Tile2D();
        tile.randomEdgeTile(side);
        node[0] = tile.getTileID();
        tileStatus[node[0]] = true;
        StdOut.print("Edge 1: " + tile.toString());
        while (node[0] == tile.getTileID()) {
            tile.randomEdgeTile(side);
        }
        node[1] = tile.getTileID();
        tileStatus[node[1]] = true;
        StdOut.println(" Edge 2: " + tile.toString());
        StdOut.println();
        return node;
    }

    private void addTile() {
        Tile2D tile;
        do  {
            tile = new Tile2D(side);
        } while (tileStatus[tile.getTileID()]);
        tileStatus[tile.getTileID()] = true;
        StdOut.println("Open tile: " + tile.toString());
        connectTile(tile);
        StdOut.println();
    }

    private void connectTile(Tile2D tile) {
        Tile2D[] nextTile = tile.nearByTile(side);
        for (int i = 0; i < nextTile.length; i++) {
            unionTile(tile, nextTile[i]);
        }
    }

    private void unionTile(Tile2D tile, Tile2D nextTile) {
        if (nextTile.valid(side) && tileStatus[nextTile.getTileID()]) {
            uf.union(tile.getTileID(), nextTile.getTileID());
            StdOut.println("Connect tile: " + tile.toString() + " " + nextTile.toString());
        }
    }
}