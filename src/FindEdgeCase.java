import java.util.Arrays;
import java.util.Random;

public class FindEdgeCase {
    public static void main(String[] args)
    {
        Random random = new Random();
        int row = random.nextInt(50) + 3;
        int column = random.nextInt(50) + 3;
        PolynomialMatchingProblem solution1 = new PolynomialMatchingProblem();
        PolynomialMatchingOtherSolution solution2 = new PolynomialMatchingOtherSolution();
        boolean flag = true;
        while(flag)
        {
            int[][] map = new int[row][column];
            for (int i = 0; i < row; i++)
            {
                for (int j = 0; j  < column; j++)
                {
                    map[i][j] = random.nextInt(100) + 1;
                }
            }
            int result1 = solution1.find(row, column, map);
            int result2 = solution2.find(row, column, map, 0);
            if (result1 == result2) {
                flag = true;
                System.out.println(flag);
            }
            else
            {
                System.out.println("false");
                solution2.find(row, column, map, result2);
                for(int i = 0; i < row; i++) {
                    System.out.println(Arrays.toString(map[i]));
                }
                System.out.println("\n" + "answer = " + result2);
                System.out.println("output = " + result1);
                flag = false;
            }
        }
    }
}
