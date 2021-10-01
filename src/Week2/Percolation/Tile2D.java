package Week2.Percolation;
public class Tile2D {
    private int x, y,
                tileID;

    Tile2D() {
        this.x = 0;
        this.y = 0;
        this.tileID = 0;
    }

    Tile2D (int side) {
        this.x = (int) Math.round(Math.random() * (side - 1));
        this.y = (int) Math.round(Math.random() * (side - 1));
        this.tileID = x * side + y;
    }

    Tile2D (int x, int y, int side) {
        this.x = x;
        this.y = y;
        this.tileID = x * side + y;
    }

    Tile2D (Tile2D tile, int xRelative, int yRelative, int side) {
        this.x = tile.x + xRelative; 
        this.y = tile.y + yRelative;
        this.tileID = this.x * side + this.y;    }

    void randomEdgeTile(int side) {
        x = (int) (Math.round(Math.random()) * (side - 1));
        y = (int) (Math.round(Math.random()) * (side - 1));
        tileID = x * side + y;
    }

    public int getTileID() {
        return tileID;
    }

    public Tile2D[] nearByTile(int side) {
        Tile2D[] nearByTile = new Tile2D[4];
        nearByTile[0] = new Tile2D(this, -1, 0, side);
        nearByTile[1] = new Tile2D(this, 1, 0, side);
        nearByTile[2] = new Tile2D(this, 0, -1, side);
        nearByTile[3] = new Tile2D(this, 0, 1, side);
        return nearByTile;
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