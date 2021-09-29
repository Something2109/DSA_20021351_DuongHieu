public class B {
    public static void main(String[] args) {
        int a = 1, b = 2;
        A ab = new A();
        System.out.println(ab.sum(a, b));
        System.out.println(ab.sum(ab.square(a), ab.square(b)));
    }
}
