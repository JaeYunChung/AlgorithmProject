package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BinaryDivisionProblem
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 3; i++)
        {
            int N = Integer.parseInt(br.readLine());
            int total = 0;
            Coin[] coins = new Coin[N + 1];
            for (int j = 1; j <= N; j++)
            {
                st = new StringTokenizer(br.readLine(), " ");
                int money = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                total += money * num;
                coins[j] = new Coin(money, num);
            }
            if (total % 2 != 0) {
                System.out.print(0);
                if (i != 2) System.out.println();
                continue;
            }
            total /= 2;
            Boolean[][] dp= new Boolean[N + 1][total + 1];
            for (int j = 0; j < N + 1; j++)
            {
                Arrays.fill(dp[j], false);
            }
            dp[0][0] = true;
            for (int j = 1; j <= N; j++)
            {
                for (int k = 0; k <= total; k++)
                {
                    if (dp[j-1][k])
                    {
                        for (int r = 0; r <= coins[j].num; r++)
                        {
                            if (k + coins[j].money * r <= total)
                            {
                                dp[j][k + coins[j].money * r] = true;
                            }
                        }
                    }
                }
            }
            if (dp[N][total]) System.out.print(1);
            else System.out.print(0);
            if (i != 2) System.out.println();
        }
    }
    public static class Coin{
        int money;
        int num;
        public Coin(int money, int num)
        {
            this.money = money;
            this.num = num;
        }
    }

}
