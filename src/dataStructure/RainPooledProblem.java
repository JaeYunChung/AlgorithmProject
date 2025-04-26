package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RainPooledProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        Integer[] block = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
       int higher = block[0];
       Deque<Integer> stack = new ArrayDeque<>();
       stack.push(block[0]);
       int result = 0;
       for (int i = 1; i < W; i++)
       {
           if(higher > block[i])
           {
               stack.push(block[i]);
               continue;
           }
           while(!stack.isEmpty())
           {
               result += higher - stack.pop();
           }
           higher = block[i];
       }
       if (!stack.isEmpty())
       {
           higher = stack.pop();
           while(!stack.isEmpty())
           {
               int entry = stack.pop();
               if (entry > higher)
               {
                   higher = entry;
                   continue;
               }
               result += higher - entry;
           }
       }
       System.out.print(result);
    }

}
