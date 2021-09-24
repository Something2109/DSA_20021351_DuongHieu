package RGBtoCMYK;
import edu.princeton.cs.algs4.*;

public class CMYK {
    int cyan, magenta, yellow, black;

    CMYK () {
        // cyan = 0;
        // magenta = 0;
        // yellow = 0;
        // black = 0;
        this.input();
    }

    CMYK (int _cyan, int _magenta, int _yellow, int _black) {
        cyan = _cyan;
        magenta = _magenta;
        yellow = _yellow;
        black = _black;
    }

    public void input() {
        System.out.print("Cyan value: ");
        cyan = StdIn.readInt();
        System.out.print("Magenta value: ");
        magenta = StdIn.readInt();
        System.out.print("Yellow value: ");
        yellow = StdIn.readInt();
        System.out.print("Black value: ");
        black = StdIn.readInt();
    }

    public String print() {
        return ("(" + cyan + "%, " + magenta + "%, " + yellow + "%, " + black + "%)");
    }

    public static void main(String[] args) {
        CMYK original = new CMYK();
        
        System.out.println(original.print());
    }
}
