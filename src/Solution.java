public class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int max = 1, min = 1;
        long interval = 0;
        int answer = 1;
        for(int i = 0; i < diffs.length; i++)
        {
            if(max < diffs[i]) max = diffs[i];
        }answer = (min+max)/2;
        while(true) {
            interval = 0;
            for (int i = 0; i < diffs.length; i++) {
                if (answer >= diffs[i]) interval += times[i];
                else {
                    interval += (diffs[i] - answer) * (times[i - 1] + times[i]) + times[i];
                }
                if (interval > limit) break;
            }
            if (interval <= limit) {
                if(answer == max) return answer;
                max = answer;
                answer = (min + answer)/2;
            }
            else {
                if(answer + 1 == max) return answer + 1;
                min = answer;
                answer = (max + answer)/2;
            }
        }
    }
}
