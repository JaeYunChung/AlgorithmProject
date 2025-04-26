import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CommonKnapProb {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] weight = new int[N];
        int[] value = new int[N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }
        int[][] backpack = new int[N + 1][W + 1];
        for (int i = 0; i <= N; i++){
            for (int j = 0; j <= W; j++){
                if (i == 0 || j == 0) backpack[i][j] = 0;
                else{
                    backpack[i][j] = backpack[i][j - 1];
                    for (int k = 0; k < N; k++){
                        if(weight[k] <= j)
                            backpack[i][j] = Math.max(backpack[i][j], value[k] + backpack[i - 1][j - weight[k]]);
                    }
                }
            }
        }
        System.out.println(backpack[N][W]);
    }

}
