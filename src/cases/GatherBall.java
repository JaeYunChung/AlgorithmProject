package cases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GatherBall {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        String arr = sc.nextLine();
        char[] balls = arr.toCharArray();
        List<Integer> distribution = new ArrayList<>();
        int startBalls = 0;
        int otherBalls = N;
        int order = 0;
        for (int i = 0; i < N ;)
        {
            int count = 0;
            while(i < N - 1 && balls[i] == balls[i + 1]){
                count++;
                i++;
            }count++; i++;
            if (order % 2 == 0)
            {
                startBalls += count;
            }
            order++;
            distribution.add(count);
        }otherBalls -= startBalls;
        if (order % 2 == 0)
        {
            System.out.println(Math.min(
                    startBalls - distribution.get(0),
                    otherBalls - distribution.get(order - 1)));
        }
        else{
            int max;
            max = distribution.get(0) < distribution.get(order - 1) ?
                    distribution.get(order - 1) : distribution.get(0);
            System.out.println(Math.min(startBalls - max, otherBalls));
        }
    }
}
