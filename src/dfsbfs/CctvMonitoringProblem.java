package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CctvMonitoringProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int[][] entry = new int[height][width];
        int[][] copyEntry = new int[height][width];
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < height; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < width; j++){
                entry[i][j] = Integer.parseInt(st.nextToken());
                if (entry[i][j] != 0)
                {
                    nodes.add(new Node(new Integer[]{i, j}, entry[i][j]));
                }
            }
        }
        int result = drawMapAndFindSquare(entry, nodes, 0, Integer.MAX_VALUE);
        System.out.println(result);
    }
    private static int drawMapAndFindSquare(int[][] entry, List<Node> nodes, int start, int square){
        if (start >= nodes.size()){
            return Math.min(square, findSquare(entry));
        }
        switch(nodes.get(start).value){
            case 1 -> {
                for (int j = 0; j < 4; j++){
                    List<Integer[]> temp = drawMap(nodes.get(start).location, new Integer[]{j}, entry);
                    square = drawMapAndFindSquare(entry, nodes, start + 1, square);
                    refreshMap(temp, entry);
                }
            }
            case 2 ->{
                for (int j = 0; j < 2; j++){
                    List<Integer[]> temp = drawMap(nodes.get(start).location, new Integer[]{j, j + 2}, entry);
                    square = drawMapAndFindSquare(entry, nodes, start + 1, square);
                    refreshMap(temp, entry);
                }
            }
            case 3 -> {
                for (int j = 0; j < 4; j++){
                    List<Integer[]> temp = drawMap(nodes.get(start).location, new Integer[]{j, (j + 1)%4}, entry);
                    square = drawMapAndFindSquare(entry, nodes, start + 1, square);
                    refreshMap(temp, entry);
                }
            }
            case 4 -> {
                for (int j = 0; j < 4; j++){
                    List<Integer[]> temp = drawMap(nodes.get(start).location, new Integer[]{j, (j + 1)%4, (j + 2)%4}, entry);
                    square = drawMapAndFindSquare(entry, nodes, start + 1, square);
                    refreshMap(temp, entry);
                }
            }
            case 5 ->{
                List<Integer[]> temp = drawMap(nodes.get(start).location, new Integer[]{0, 1, 2, 3}, entry);
                square = drawMapAndFindSquare(entry, nodes, start + 1, square);
                refreshMap(temp, entry);
            }
            default -> square = drawMapAndFindSquare(entry, nodes, start + 1, square);
        }
        return square;
    }

    private static List<Integer[]> drawMap(Integer[] location, Integer[] direction, int[][] entry){
        int height = entry.length;
        int width = entry[0].length;
        List<Integer[]> returnValue = new ArrayList<>();
        for (Integer d : direction)
        {
            switch(d){
                case 0 -> {
                    for (int i = location[1] + 1; i < width; i++)
                    {
                        if(entry[location[0]][i] == 0)
                        {
                            entry[location[0]][i] = -1;
                            returnValue.add(new Integer[]{location[0], i});
                        }
                        if(entry[location[0]][i] == 6) break;
                    }
                }
                case 1 -> {
                    for (int i = location[0] + 1; i < height; i++)
                    {
                        if(entry[i][location[1]] == 0)
                        {
                            entry[i][location[1]] = -1;
                            returnValue.add(new Integer[]{i, location[1]});
                        }
                        if(entry[i][location[1]] == 6) break;
                    }
                }
                case 2 -> {
                    for (int i = location[1] - 1; i >= 0; i--)
                    {
                        if(entry[location[0]][i] == 0)
                        {
                            entry[location[0]][i] = -1;
                            returnValue.add(new Integer[]{location[0], i});
                        }
                        if(entry[location[0]][i] == 6) break;
                    }
                }
                case 3 -> {
                    for (int i = location[0] - 1; i >= 0; i--)
                    {
                        if(entry[i][location[1]] == 0)
                        {
                            entry[i][location[1]] = -1;
                            returnValue.add(new Integer[]{i, location[1]});
                        }
                        if(entry[i][location[1]] == 6) break;
                    }
                }
            }
        }
        return returnValue;
    }

    private static void refreshMap(List<Integer[]> temp, int[][] entry){
        for (Integer[] location : temp){
            entry[location[0]][location[1]] = 0;
        }
    }
    private static int findSquare(int[][] entry){
        int result = 0;
        for(int i = 0; i < entry.length; i++)
        {
            for (int j = 0; j < entry[0].length; j++)
            {
                if (entry[i][j] == 0) result++;
            }
        }
        return result;
    }

    public static class Node{
        public Integer[] location;
        public int value;
        public Node(Integer[] location, int value){
            this.location = location;
            this.value = value;
        }
    }
}
