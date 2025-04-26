package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class ChainProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int kindness = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] sushi = new int[N];
        for (int i = 0 ; i < N; i++)
        {
            sushi[i] = Integer.parseInt(br.readLine());
        }
        int result = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0 ;i < N; i++)
        {
            for (int j = 0; j < k; j++)
            {
                if (sushi[(i+j)%N] == c) continue;
                set.add(sushi[(i+j)%N]);
            }
            result = Math.max(result, set.size());
            set.clear();
        }
        System.out.println(result + 1);
    }
}
