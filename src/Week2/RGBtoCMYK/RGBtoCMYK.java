package Week2.RGBtoCMYK;
public class RGBtoCMYK {
    public static void main(String[] args) {
        RGB original = new RGB();
        CMYK result = original.RGBtoCMYK();
        System.out.println("RGB: " + original.print() + " is CMYK: " + result.print());
    }
}
