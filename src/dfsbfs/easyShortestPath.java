package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class easyShortestPath {
    public static void main(String[] args)throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int rows = Integer.parseInt(st.nextToken()), columns = Integer.parseInt(st.nextToken());
        int[][] maps = new int[rows][columns];
        boolean[][] isFind = new boolean[rows][columns];
        StringBuilder builder = new StringBuilder();
        int distX=0, distY=0;

        for(int i = 0; i < rows; i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int j = 0;
            while(st.hasMoreTokens())
            {
                maps[i][j] = Integer.parseInt(st.nextToken());
                isFind[i][j] = false;
                if (maps[i][j] == 0)
                {
                    isFind[i][j] = true;
                }
                else if (maps[i][j] == 2)
                {
                    distY = i;
                    distX = j;
                    maps[distY][distX] = 0;
                    isFind[distY][distX] = true;
                }
                j++;
            }
        }
        fillMap(maps, isFind, distX, distY);
        for (int i = 0; i < rows; i++)
        {
            for(int j = 0 ;  j < columns; j++)
            {
                if (!isFind[i][j]) builder.append("-1" + " ");
                else builder.append(maps[i][j] + " ");
            }
            builder.append("\n");
        }
        System.out.println(builder);

    }
    public static void fillMap(int[][] maps, boolean[][] isFind, int startX, int startY)
    {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(startY); queue.add(startX);
        int[] moveX = {1, -1, 0, 0};
        int[] moveY = {0, 0, 1, -1};
        while(!queue.isEmpty()) {
            startY = queue.poll(); startX = queue.poll();
            for(int i = 0; i < 4; i ++)
            {
                if((0 <= startX + moveX[i] && startX + moveX[i] < maps[0].length)&&
                        (0 <= startY + moveY[i] && startY + moveY[i] < maps.length) &&
                        (!isFind[startY + moveY[i]][startX + moveX[i]]))
                {
                    maps[startY + moveY[i]][startX + moveX[i]] += maps[startY][startX];
                    isFind[startY + moveY[i]][startX + moveX[i]] = true;
                    queue.add(startY + moveY[i]); queue.add(startX + moveX[i]);
                }
            }
        }
    }
}
