package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MatrixMultiplicationProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        Integer[][] Matrix = new Integer[N][N];
        for (int i = 0; i < N; i++){
            Matrix[i] = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
        }
        Integer[][] resultMatrix = new Integer[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (i == j)resultMatrix[i][j] = 1;
                else resultMatrix[i][j] = 0;
            }
        }
        List<Integer> power = getTwoPowerSum(B);
        for (Integer e : power) {
            Integer[][] temp = Matrix;
            for (int i = 0; i < e; i++){
                temp = multipleMatrix(temp, temp, N);
                reduceMatrix(temp, N);
            }
            resultMatrix = multipleMatrix(resultMatrix, temp, N);
            reduceMatrix(resultMatrix, N);
        }
        for (int i = 0; i < N; i++){
           for (int j = 0; j < N; j++){
               System.out.print(resultMatrix[i][j] % 1000 + " ");
           }
           System.out.println();
        }
    }
    public static void reduceMatrix(Integer[][] M, int N){
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++){
                if (M[i][j] < 1000) return;
            }
        }
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++){
                M[i][j] %= 1000;
            }
        }
    }

    public static Integer[][] multipleMatrix(Integer[][] M1, Integer[][] M2, int N){
        Integer[][] resultMatrix = new Integer[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                resultMatrix[i][j] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    resultMatrix[i][j] += M1[i][k] * M2[k][j];
                }
            }
        }return resultMatrix;
    }
    public static List<Integer> getTwoPowerSum(int N){
        List<Integer> list = new LinkedList<>();
        while(N != 0) {
            int k = 0;
            while (Math.pow(2, k) <= N) {
                k++;
            }
            N -= Math.pow(2, k - 1);
            list.add(0, k - 1);
        }
        return list;
    }
}
