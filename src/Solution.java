import java.util.Arrays;

public class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int[] diffList = Arrays.stream(diffs).sorted().toArray();
        int answer = 1;
        long interval = 0;
        for(int diff : diffList) {
            interval = 0;
            for (int i = 0; i < diffs.length; i++) {
                if (diff >= diffs[i]) interval += times[i];
                else {
                    interval += (diffs[i] - diff) * (times[i - 1] + times[i]) + times[i];
                }
                if(interval > limit) break;
            }
            if(interval <= limit) {
                answer = diff;
                break;
            }
        }
        if(answer == 1) return answer;
        int leftshiftsum = 0;
        for(int i = 0; i < diffs.length; i++)
        {
            if(answer <= diffs[i]) leftshiftsum += times[i - 1] + times[i];
        }
        while(interval <= limit)
        {
            interval += leftshiftsum;
            answer--;
        }
        return answer+1;
    }
}
