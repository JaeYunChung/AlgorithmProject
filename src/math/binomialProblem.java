package math;

import java.util.Scanner;

public class binomialProblem {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long N = Long.parseLong(sc.next());
        long K = Long.parseLong(sc.next());


    }
    public static int getGCD(int a, int b){
        int remain = a % b;
        if (remain == 0)return b;

        return getGCD(b, remain);
    }
}
