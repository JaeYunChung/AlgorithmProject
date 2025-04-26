package javaGrammer;

public class Swap {
    public static void main(String[] args)
    {
        int a = 3;
        int b = 2;
        swap(a, b);
        System.out.println("a: "+ a + ", b: " + b);
    }
    public static void swap(int a, int b)
    {
        int temp = a;
        a = b;
        b = temp;
    }
}
