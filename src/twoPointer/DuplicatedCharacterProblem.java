package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DuplicatedCharacterProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < N; i++){
            String str = br.readLine();
            int k = Integer.parseInt(br.readLine());
            int[] result = findResult(twoPointer(str, new int[str.length()], k));
            if (result[0] == -1) sb.append(-1).append("\n") ;
            else sb.append(result[0]).append(" ").append(result[1]).append("\n");
        }
        System.out.print(sb.toString());
    }
    public static int[] findResult(int[] temp){
        int min = Integer.MAX_VALUE, max = 0;
        for (int i = 0; i < temp.length; i++){
            if (temp[i] == 0) continue;
            min = Math.min(min, temp[i]);
            max = Math.max(max, temp[i]);
        }
        if (min == Integer.MAX_VALUE && max == 0) return new int[]{-1};
        return new int[]{min, max};
    }
    public static int[] twoPointer(String str, int[] temp, int k){
        int count = 0;
        for (int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            for (int j = i; j < str.length(); j++)
            {
                if (str.charAt(j) == ch) count++;
                if (count == k)
                {
                    temp[i] = j - i + 1;
                    break;
                }
            }
            count = 0;
        }
        return temp;
    }
}
