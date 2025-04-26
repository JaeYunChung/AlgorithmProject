package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SumCloseToZero
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++)
        {
            int temp = Integer.parseInt(st.nextToken());
            list.add(temp);
        }
        Integer[] sortedStr = list.stream().sorted().toArray(Integer[]::new);
        int start = 0, end = N - 1;
        int result = Integer.MAX_VALUE;
        int[] value = new int[2];
        while(start < end)
        {
            int sum = sortedStr[start] + sortedStr[end];
            if (Math.abs(result) > Math.abs(sum))
            {
                result = sum;
                value[0] = sortedStr[start];
                value[1] = sortedStr[end];
            }
            if (sum == 0) break;
            else if (sum < 0)
            {
                start++;
            }
            else
            {
                end--;
            }
        }
        System.out.print(value[0] + " " + value[1]);
    }
}
