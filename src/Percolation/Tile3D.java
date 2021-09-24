public class Tile3D {
    private int x, y, z,
                tileID;

    Tile3D (int edge) {
        this.x = (int) Math.round(Math.random() * (edge - 1));
        this.y = (int) Math.round(Math.random() * (edge - 1));
        this.z = (int) Math.round(Math.random() * (edge - 1));
        this.tileID = x * edge + y + z * edge * edge;
    }

    Tile3D (int x, int y, int z, int edge) {
        this.x = x;
        this.y = y;
        this.z = z;
        tileID = x * edge + y + z * edge * edge;
    }

    Tile3D (Tile3D tile, int xRelative, int yRelative, int zRelative, int edge) {
        setFromTile(tile, xRelative, yRelative, zRelative, edge);
    }

    public int getTileID() {
        return tileID;
    }

    public void setFromTile(Tile3D tile, int xRelative, int yRelative, int zRelative, int edge) {
        this.x = tile.x + xRelative; 
        this.y = tile.y + yRelative;
        this.z = tile.z + zRelative;
        this.tileID = this.x * edge + this.y + this.z * edge * edge;
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
