package javaGrammer;

import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] arg){
        String line = "Hello world";
        Scanner sc = new Scanner(line);
        System.out.println(sc.next());
        System.out.println(sc.next());
    }
}
