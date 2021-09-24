import edu.princeton.cs.algs4.*;

public class Percolation3D {
    int field, edge;
    UF uf;
    boolean[] tileStatus;

    Percolation3D(int n) {
        edge = n;
        field = n * n + 2;
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
                Tile2D tile = new Tile2D(i, j, edge);
                if (i == 0) {
                    uf.union(field - 2, tile.getTileID());
                }
                else if (i == edge - 1){
                    uf.union(field - 1, tile.getTileID());
                }
            }
        }
        StdOut.println();
    }

    private void addTile() {
        Tile2D tile;
        do  {
            tile = new Tile2D(edge);
        } 
        while (tileStatus[tile.getTileID()]);
        tileStatus[tile.getTileID()] = true;
        StdOut.println("Open tile: " + tile.toString());
        connectTile(tile);
        StdOut.println();
    }

    private void connectTile(Tile2D tile) {
        Tile2D nextTile = new Tile2D(tile, -1, 0, edge);
        unionTile(tile, nextTile);
        nextTile.setFromTile(tile, 1, 0, edge);
        unionTile(tile, nextTile);
        nextTile.setFromTile(tile, 0, -1, edge);
        unionTile(tile, nextTile);
        nextTile.setFromTile(tile, 0, 1, edge);
        unionTile(tile, nextTile);
    }

    private void unionTile(Tile2D tile, Tile2D nextTile) {
        if (nextTile.valid(edge) && tileStatus[nextTile.getTileID()]) {
            uf.union(tile.getTileID(), nextTile.getTileID());
            StdOut.println("Connect tile: " + tile.toString() + " " + nextTile.toString());
        }
    }
}
