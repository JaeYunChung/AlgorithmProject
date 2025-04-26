package math;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class multiplication {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long a = Long.parseLong(sc.next());
        long b = Long.parseLong(sc.next());
        long c = Long.parseLong(sc.next());

        a %= c;

        long temp = a;
        long result = 1;
        List<Integer> powerSumList = makeTwoPowerSum(b);
        int start = 0;
        for (Integer e : powerSumList) {
            for (int i = start; i < e; i++) {
                temp *= temp;
                if (temp > c)
                    temp %= c;
            }
            result *= temp;
            result %= c;
            start = e;
        }
        System.out.println(result);
    }
    public static List<Integer> makeTwoPowerSum(long num){
        List<Integer> result = new ArrayList<>();
        while(num != 0) {
            int k = 0;
            while (Math.pow(2, k) <= num) {
                k++;
            }
            num -= Math.pow(2, k - 1);
            result.add(0, k - 1);
        }return result;
    }
}
