import edu.princeton.cs.algs4.*;

public class RGB {
    int red, green, blue;

    RGB () {
        // red = 255;
        // green = 255;
        // blue = 255;
        this.input();
    }

    RGB (int _red, int _green, int _blue) {
        red = _red;
        green = _green;
        blue = _blue;
    }

    public void input() {
        System.out.print("Red value: ");
        red = StdIn.readInt();
        System.out.print("Green value: ");
        green = StdIn.readInt();
        System.out.print("Blue value: ");
        blue = StdIn.readInt();
    }

    public String print() {
        return ("(" + red + ", " + green + ", " + blue + ")");
    }

    public CMYK RGBtoCMYK () {
        double white = Math.max(Math.max(red / 255.0, green / 255.0), blue / 255.0);
        int cyan = (int) Math.round((white - red / 255.0) / white * 100), 
            magenta = (int) Math.round((white - green / 255.0) / white * 100), 
            yellow = (int) Math.round((white - blue / 255.0) / white * 100),
            black = (int) Math.round((1 - white) * 100);
        CMYK result = new CMYK(cyan, magenta, yellow, black);
        return result;
    }

    public static void main(String[] args) throws Exception {
        RGB original = new RGB();
        
        System.out.println(original.print());
    }
}