import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PolynomialMatchingProblem
{
    public static int row;
    public static int column;
    public int find(int r, int c, int[][] map){
        int result = 0;
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        row = Integer.parseInt(st.nextToken());
//        column = Integer.parseInt(st.nextToken());
//        Integer[][] map = new Integer[row][column];
//        for (int i = 0; i < row; i++)
//        {
//            map[i] = Arrays.stream(br.readLine().split(" "))
//                    .map(Integer::parseInt)
//                    .toArray(Integer[]::new);
//        }
        row = r;
        column = c;
        Deque<Tern> queue = new ArrayDeque<>();
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < column; j++)
            {
                List<Tern> cases = new ArrayList<>();
                Tern start = new Tern(new Integer[]{i, j});
                queue.add(start);
                while(!queue.isEmpty())
                {
                    Tern e = queue.poll();
                    if (e.count == 4)
                    {
                        cases.add(e);
                        continue;
                    }
                    Integer[] presentLoc = e.location.get(e.location.size() - 1);
                    makeCases(queue, presentLoc, e);
                }
                makeSpecialCase(new Integer[]{i, j}, cases);
                for (Tern l : cases)
                {
                    int count = 0;
                    for (int k = 0; k < 4; k++)
                    {
                        Integer[] loc = l.location.get(k);
                        count += map[loc[0]][loc[1]];
                    }
                    result = Math.max(result, count);
                }
            }
        }
        return result;
    }
    public static class Tern{
        List<Integer[]> location;
        int count;
        public Tern(Integer[] l) {
            this.location = new ArrayList<>();
            this.location.add(l);
            this.count = 1;
        }
        public Tern(Integer[] l, List<Integer[]> pre, int count)
        {
            this.location = new ArrayList<>(pre);
            this.location.add(l);
            this.count = count;
        }
        public boolean contains(Integer[] arr)
        {
            for (Integer[] l : location)
            {
                if (l[0] == arr[0] && l[1] == arr[1])
                    return true;
            }
            return false;
        }
    }
    public static void makeCases(Deque<Tern> queue, Integer[] presentLoc, Tern e){
        int[] moveX = {1, 0, 0};
        int[] moveY = {0, 1, -1};
        for (int i = 0; i < 3; i++)
        {
            if (0 <= presentLoc[0] + moveX[i] && presentLoc[0] + moveX[i] < row
            && 0 <= presentLoc[1] + moveY[i] && presentLoc[1] + moveY[i] < column)
            {
                Integer[] next = new Integer[]{presentLoc[0] + moveX[i], presentLoc[1] + moveY[i]};
                if (!e.contains(next))
                {
                    queue.add(new Tern(next, e.location, e.count + 1));
                }
            }
        }
    }
    public static void makeSpecialCase(Integer[] start, List<Tern> cases){
        int[] moveX = {1, -1, 0, 0};
        int[] moveY = {0, 0, 1, -1};
        for (int i = 0; i < 4; i++)
        {
            boolean flag = true;
            List<Integer[]> temp = new ArrayList<>();
            for (int j = 0; j < 4; j++)
            {
                if (j == i) continue;
                if (0 <= start[0] + moveX[j] && start[0] + moveX[j] < row
                && 0 <= start[1] + moveY[j] && start[1] + moveY[j] < column)
                    temp.add(new Integer[]{start[0] + moveX[j], start[1] + moveY[j]});
                else flag = false;
            }
            if (flag)
            {
                cases.add(new Tern(start, temp, 0));
            }
        }
    }
}
