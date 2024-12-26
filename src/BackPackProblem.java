import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * GG 너무 어렵다
 */

public class BackPackProblem {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = br.readLine();
        StringTokenizer st = new StringTokenizer(firstLine, " ");
        int totalNumber = Integer.parseInt(st.nextToken());
        int maxWeight = Integer.parseInt(st.nextToken());
        int[][] memo = new int[totalNumber][maxWeight];
        for(int i = 0 ; i < totalNumber ; i++)
        {
            for(int j = 0; j < maxWeight; j++) {
                memo[i][j] = -1;
            }
        }
        int[][] weightAndValue = new int[totalNumber][2];
        List<int[]> pq = new ArrayList<>();
        for(int i = 0; i < totalNumber; i++)
        {
            String line = br.readLine();
            st = new StringTokenizer(line, " ");
            weightAndValue[i][0] = Integer.parseInt(st.nextToken());
            weightAndValue[i][1] = Integer.parseInt(st.nextToken());
            pq.add(weightAndValue[i]);
        }
        pq = pq.stream().sorted((o1, o2)-> -o1[0] + o2[0]).collect(Collectors.toList());
        int maxValue = 0;
        maxValue = findMaxValue(0, pq, pq.get(0)[0], 0, maxValue, memo);
        System.out.println(maxValue);
    }
    public static int findMaxValue(int start, List<int[]> pq, int presentWeight, int presentValue, int maxValue, int[][] memo)
    {
        if(presentWeight < pq.get(pq.size() - 1)[0] && maxValue < presentValue) {
            return presentValue;
        }
        for (int i = start; i < pq.size(); i++)
        {
            if (presentWeight - pq.get(i)[0] >= 0)
            {
                presentWeight -= pq.get(i)[0];
                presentValue += pq.get(i)[1];
                maxValue = findMaxValue(start + 1, pq, presentWeight, presentValue, maxValue, memo);
            }
            presentWeight += pq.get(i)[0];
            presentValue -= pq.get(i)[1];
        }
        return maxValue;
    }
}
