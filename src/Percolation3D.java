import edu.princeton.cs.algs4.*;

public class Percolation3D {
    int field, edge;
    UF uf;
    boolean[] tileStatus;

    Percolation3D(int n) {
        edge = n;
        field = n * n * n + 2;
        uf = new UF(field);
        tileStatus = new boolean[field];
        StdOut.println("Create field: " + edge + ", " + field);
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int tileCount = 0;
        Percolation3D per = new Percolation3D(n);
        per.settingUp();
        while (!per.uf.connected((per.field - 2), (per.field - 1)) && tileCount < per.field - 2) {
            per.addTile();
            tileCount ++;
        }
        StdOut.println(tileCount);
    }

    private void settingUp() {
        for (int i = 0; i < edge; i++) {
            for (int j = 0; j < edge; j++) {
                Tile3D tile = new Tile3D(i, j, 0, edge);
                uf.union(field - 2, tile.getTileID());
                Tile3D tile1 = new Tile3D(i, j, edge - 1, edge);
                uf.union(field - 1, tile1.getTileID());
            }
        }
        StdOut.println();
    }

    private void addTile() {
        Tile3D tile;
        do  {
            tile = new Tile3D(edge);
        } 
        while (tileStatus[tile.getTileID()]);
        tileStatus[tile.getTileID()] = true;
        StdOut.println("Open tile: " + tile.toString());
        connectTile(tile);
        StdOut.println();
    }

    private void connectTile(Tile3D tile) {
        Tile3D nextTile = new Tile3D(tile, -1, 0, 0,edge);
        unionTile(tile, nextTile);
        nextTile.setFromTile(tile, 1, 0, 0, edge);
        unionTile(tile, nextTile);
        nextTile.setFromTile(tile, 0, -1, 0, edge);
        unionTile(tile, nextTile);
        nextTile.setFromTile(tile, 0, 1, 0, edge);
        unionTile(tile, nextTile);
        nextTile.setFromTile(tile, 0, 0, -1, edge);
        unionTile(tile, nextTile);
        nextTile.setFromTile(tile, 0, 0, 1, edge);
        unionTile(tile, nextTile);
    }

    private void unionTile(Tile3D tile, Tile3D nextTile) {
        if (nextTile.valid(edge) && tileStatus[nextTile.getTileID()]) {
            uf.union(tile.getTileID(), nextTile.getTileID());
            StdOut.println("Connect tile: " + tile.toString() + " " + nextTile.toString());
        }
    }
}
