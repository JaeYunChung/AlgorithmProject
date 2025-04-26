package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class mineralProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int bottom = row - 1;

        String[][] map = new String[row][col];
        for (int i = 0; i < row; i++)
        {
            map[i] = Arrays.stream(br.readLine().split(""))
                    .flatMap(str -> {
                        if (str.startsWith("x")) {
                            return Arrays.stream(str.split(""));
                        }
                        return Arrays.stream(new String[]{str});})
                    .toArray(String[]::new);
        }
        int num = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < num; i++)
        {
            Integer[] location = throwBar(map, Integer.parseInt(st.nextToken()), i);
            if (location.length != 0)
            {
                List<Integer[]> cluster = returnSeperatedCluster(map, location);
                if (cluster != null)
                {
                    boolean flag = true;
                    while(flag)
                    {
                        for (Integer[] element : cluster)
                        {
                            if (element[0] + 2 > bottom || map[element[0] + 2][element[1]].equals("x"))
                                flag = false;
                            map[element[0]][element[1]] = ".";
                            map[element[0] + 1][element[1]] = "o";
                            element[0] = element[0] + 1;
                        }
                    }
                    for (Integer[] element : cluster)
                    {
                        map[element[0]][element[1]] = "x";
                    }
                }
            }
        }
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                System.out.print(map[i][j]);
            }
            if(i != row - 1) System.out.println();
        }
    }
    private static Integer[] throwBar(String[][] map, int height, int order)
    {
        int totalHeight = map.length;
        int totalWidth = map[0].length;
        String[] barHeight = map[totalHeight - height];
        if (order % 2 == 0)
        {
            for (int i = 0; i < totalWidth; i++)
            {
                if (barHeight[i].equals("x"))
                {
                    barHeight[i] = ".";
                    return new Integer[]{totalHeight - height, i};
                }
            }
        }
        else {
            for (int i = totalWidth - 1; i >= 0 ; i--)
            {
                if (barHeight[i].equals("x"))
                {
                    barHeight[i] = ".";
                    return new Integer[]{totalHeight - height, i};
                }
            }
        }
        return new Integer[]{};
    }

    private static List<Integer[]> returnSeperatedCluster(String[][] map, Integer[] start)
    {
        int[] rowShift = {1, -1, 0, 0};
        int[] colShift = {0, 0, 1, -1};
        List<Integer[]> startLocation = new ArrayList<>();
        for (int i = 0 ; i < 4; i++)
        {
            if (isBetween(start[0] + rowShift[i], 0, map.length)
            && isBetween(start[1] + colShift[i], 0, map[0].length))
                if(map[start[0] + rowShift[i]][start[1] + colShift[i]].equals("x"))
                    startLocation.add(new Integer[]{start[0] + rowShift[i], start[1] + colShift[i]});
        }
        List<Integer[]> buffer = new ArrayList<>();
        for (Integer[] location: startLocation)
        {
            if (eachBfs(map, location, buffer)) {
                for(int i = 0; i < map.length; i++)
                {
                    for (int j = 0; j < map[0].length; j++)
                    {
                        if (map[i][j].equals("o")) map[i][j] = "x";
                    }
                    for (Integer[] element : buffer)
                    {
                        map[element[0]][element[1]] = "o";
                    }
                }
                return buffer;
            }
            buffer.clear();
        }
        for(String[] row : map)
        {
            for (int i = 0; i < row.length; i++)
            {
                if (row[i].equals("o")) row[i] = "x";
            }
        }
        return null;
    }

    private static boolean eachBfs(String[][] map, Integer[] location, List<Integer[]> buffer)
    {
        int[] rowShift = {1, -1, 0, 0};
        int[] colShift = {0, 0, 1, -1};
        int bottom = map.length - 1;

        Deque<Integer[]> queue = new ArrayDeque<>();
        boolean seperateFlag = true;
        if (map[location[0]][location[1]].equals("o")) return false;
        queue.add(location);
        map[location[0]][location[1]] = "o";
        while(!queue.isEmpty())
        {
            Integer[] e = queue.poll();
            buffer.add(e);
            if (e[0] == bottom)
            {
                seperateFlag = false;
                //break;
            }
            for (int i = 0; i < 4; i++)
            {
                if(isBetween(e[0]+rowShift[i], 0, map.length)
                        && isBetween(e[1]+colShift[i], 0, map[0].length))
                    if (map[e[0] + rowShift[i]][e[1] + colShift[i]].equals("x"))
                    {
                        queue.add(new Integer[]{e[0] + rowShift[i], e[1] + colShift[i]});
                        map[e[0] + rowShift[i]][e[1] + colShift[i]] = "o";
                    }
            }
        }
        for(int i = 0; i < map.length; i++)
        {
            System.out.println(Arrays.toString(map[i]));
        }System.out.println();
        return seperateFlag;
    }

    private static boolean isBetween(int element, int start, int end)
    {
        if (start <= element && element < end) return true;
        return false;
    }
}
