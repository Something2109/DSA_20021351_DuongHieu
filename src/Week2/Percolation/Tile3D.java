package Week2.Percolation;
public class Tile3D {
    private int x, y, z,
                tileID;

    Tile3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.tileID = 0;
    }

    Tile3D (int side) {
        this.x = (int) Math.round(Math.random() * (side - 1));
        this.y = (int) Math.round(Math.random() * (side - 1));
        this.z = (int) Math.round(Math.random() * (side - 1));
        this.tileID = x * side + y + z * side * side;
    }

    Tile3D (int x, int y, int z, int side) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.tileID = x * side + y + z * side * side;
    }

    Tile3D (Tile3D tile, int xRelative, int yRelative, int zRelative, int side) {
        this.x = tile.x + xRelative; 
        this.y = tile.y + yRelative;
        this.z = tile.z + zRelative;
        this.tileID = this.x * side + this.y + this.z * side * side;
    }

    void randomEdgeTile(int side) {
        x = (int) (Math.round(Math.random()) * (side - 1));
        y = (int) (Math.round(Math.random()) * (side - 1));
        z = (int) (Math.round(Math.random()) * (side - 1));
        this.tileID = this.x * side + this.y + this.z * side * side;
    }

    public int getTileID() {
        return tileID;
    }        

    public Tile3D[] nearByTile(int side) {
        Tile3D[] nearByTile = new Tile3D[6];
        nearByTile[0] = new Tile3D(this, -1, 0, 0,side);
        nearByTile[1] = new Tile3D(this, 1, 0, 0, side);
        nearByTile[2] = new Tile3D(this, 0, -1, 0, side);
        nearByTile[3] = new Tile3D(this, 1, 0, 0, side);
        nearByTile[4] = new Tile3D(this, 0, 0, -1, side);
        nearByTile[5] = new Tile3D(this, 0, 0, 1, side);
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
        else if (z < 0 || z >= edge) {
            valid = false;
        } 
        return valid;
    }

    public void openTile(Boolean[] tileStatus) {
        tileStatus[tileID] = true;
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}