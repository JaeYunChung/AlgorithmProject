package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BackPackProblem {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int limit = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][limit + 1];
        int[] weight = new int[N + 1]; int[] value = new int[N + 1];
        for (int i = 1 ; i <= N; i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i <= N; i++)
        {
            for (int j = 0; j <= limit; j++)
            {
                if (i == 0 || j == 0)
                {
                    dp[i][j] = 0;
                    continue;
                }
                // i번째 물건을 담지 않는 경우는 dp[i - 1][p]
                // i번째 물건을 담는 경우는 dp[i - 1][p - weight[i]] + value[i]
                if (j >= weight[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        System.out.print(dp[N][limit]);
    }

}
