package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ExecutorProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] students = new int[N];
        for (int i = 0 ; i < N; i++)
        {
            students[i] = Integer.parseInt(st.nextToken());
        }
        int[] executor = new int[2];
        st = new StringTokenizer(br.readLine(), " ");
        executor[0] = Integer.parseInt(st.nextToken());
        executor[1] = Integer.parseInt(st.nextToken());
        long count = 0;
        for(int student : students)
        {
            student -= executor[0];
            count += 1;
            if (student <= 0) continue;
            if (student % executor[1] == 0) count += student / executor[1];
            else count += student / executor[1] + 1;
        }
        System.out.print(count);
    }
}
