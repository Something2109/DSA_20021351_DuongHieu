package Percolation;
public class Tile2D {
    private int x, y,
                tileID;

    Tile2D (int edge) {
        this.x = (int) Math.round(Math.random() * (edge - 1));
        this.y = (int) Math.round(Math.random() * (edge - 1));
        this.tileID = x * edge + y;
    }

    Tile2D (int tileID, int edge) {
        setTileID(tileID, edge);
    }

    Tile2D (int x, int y, int edge) {
        this.x = x;
        this.y = y;
        tileID = x * edge + y;
    }

    Tile2D (Tile2D tile, int xRelative, int yRelative, int edge) {
        setFromTile(tile, xRelative, yRelative, edge);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTileID() {
        return tileID;
    }

    public void setTileID(int tileID, int edge) {
        this.tileID = tileID;
        this.x = tileID % edge;
        this.y = tileID / edge;
    }

    public void setFromTile(Tile2D tile, int xRelative, int yRelative, int edge) {
        this.x = tile.x + xRelative; 
        this.y = tile.y + yRelative;
        this.tileID = this.x * edge + this.y;
    }

    public boolean valid(int edge) {
        boolean valid = true;
        if (x < 0 || x >= edge) {
            valid = false;
        }
        else if (y < 0 || y >= edge) {
            valid = false;
        } 
        return valid;
    }

    public void openTile(Boolean[] tileStatus) {
        tileStatus[tileID] = true;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
