package dfsbfs;

import java.util.*;

public class MovingProblem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int finder = Integer.parseInt(sc.next());
        int hinder = Integer.parseInt(sc.next());

        if (finder >= hinder) {
            System.out.print(finder - hinder);
        } else {
            int[] find = new int[2 * hinder + 1];
            Arrays.fill(find, Integer.MAX_VALUE);
            Deque<Integer> queue = new ArrayDeque<>();

            queue.add(finder);
            find[finder] = 0;

            while (!queue.isEmpty())
            {
                int present = queue.poll();
                int[] move = {1, -1, present};
                int[] count = {1, 1, 0};


                for (int i = 0; i < 3; i++)
                {
                    if (0 <= present + move[i] && present +move[i] <= 2* hinder && find[present + move[i]] > find[present] + count[i])
                    {
                        find[present + move[i]] = find[present] + count[i];
                        queue.add(present + move[i]);
                    }
                }

            }
            System.out.print(find[hinder]);
        }
    }
}
