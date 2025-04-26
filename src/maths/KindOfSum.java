package maths;

import java.util.Scanner;

public class KindOfSum {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < T; i++)
        {
            int n = Integer.parseInt(sc.nextLine());
            s.append(findCase(n));
            if (i != T - 1) s.append("\n");
        }
        System.out.print(s);
    }
    public static int findCase(int n)
    {
        int result = 0;
        int remain = n % 3;
        int quotient = n / 3;
        for (int i = 1; i <= quotient; i++)
        {
            result += 1 + (remain + i * 3) / 2;
        }
        if (remain == 2) return 2 + result;
        else return 1 + result;
    }
}
