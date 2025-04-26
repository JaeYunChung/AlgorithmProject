package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DiceMovingProblem
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int numOfOp = Integer.parseInt(st.nextToken());
        int[][] map = new int[height][width];
        for (int i = 0; i < height; i++)
        {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < width; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Integer[] ops = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        int[] row = new int[3];
        int[] column = new int[4];
        row[1] = map[x][y];
        column[1] = map[x][y];
        map[x][y] = 0;
        for (Integer op : ops)
        {
            switch(op)
            {
                case 1:

                    if (y + 1 >= width) break;
                    y++;
                    moveDice(row, "right");
                    column[1] = row[1];
                    swap(row, 0, column, 3);
                    doLogic(map, x, y, column);
                    System.out.println(row[1]);
                    break;
                case 2:
                    if (y - 1 < 0) break;
                    y--;
                    moveDice(row, "left");
                    column[1] = row[1];
                    swap(row, 2, column, 3);
                    doLogic(map, x, y, column);
                    System.out.println(row[1]);
                    break;
                case 3:
                    if (x - 1 < 0) break;
                    x--;
                    moveDice(column, "left");
                    row[1] = column[1];
                    doLogic(map, x, y, column);
                    System.out.println(row[1]);
                    break;
                case 4:
                    if (x + 1 >= height) break;
                    x++;
                    moveDice(column, "right");
                    row[1] = column[1];
                    doLogic(map, x, y, column);
                    System.out.println(row[1]);
                    break;
            }
        }
    }

    private static void doLogic(int[][] map, int x, int y, int[] column) {
        if (map[x][y] == 0) map[x][y] = column[3];
        else
        {
            column[3] = map[x][y];
            map[x][y] = 0;
        }
    }

    public static void swap(int[] row, int x, int[] column, int y)
    {
        int temp = row[x];
        row[x] = column[y];
        column[y] = temp;
    }
    public static void moveDice(int[] dice, String direction)
    {
        if (direction.equals("left"))
        {
            int temp = dice[0];
            for (int i = 1; i < dice.length; i++)
            {
                dice[i - 1] = dice[i];
            }
            dice[dice.length - 1] = temp;
        }
        else
        {
            int temp = dice[dice.length - 1];
            for (int i = dice.length - 1; i >= 1; i--)
            {
                dice[i] = dice[i - 1];
            }
            dice[0] = temp;
        }
    }
}
