package math;

import java.util.Scanner;

public class ExchangeOfString {
    public static void main(String[] args)
    {
        int count = 0; char ch = 'a';
        Scanner sc = new Scanner(System.in);
        char[] arr = sc.nextLine().toCharArray();
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] == ch)count++;
        }
        int result = arr.length;
        int first = count;
        int second = arr.length - count;
        result = findMin(arr, result, first, 'b');
        result = findMin(arr, result, second, 'a');
        System.out.print(result);
    }
    public static int findMin(char[] arr, int result, int count, char ch)
    {
        for (int i = 0; i < arr.length - count; i++)
        {
            result = Math.min(result, countChar(arr, ch, i, i + count));
        }
        return result;
    }
    public static int countChar(char[] arr, char ch, int start, int end){
        int count = 0;
        for (int i = start; i < end; i++)
        {
            if (arr[i] == ch) count++;
        }
        return count;
    }

}
