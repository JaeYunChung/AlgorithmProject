package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ShortestArrayLengthProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int arrayLength = Integer.parseInt(st.nextToken());
        int leastDuplicated = Integer.parseInt(st.nextToken());
        Integer[] array = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        int[] duplicate = new int[100_001];
        int start = 0, pointer;
        int maxLength = 0;
        for (pointer = 0; pointer < arrayLength; pointer++){
            int value = ++duplicate[array[pointer]];
            if (value > leastDuplicated){
                while(!array[start].equals(array[pointer])){
                    duplicate[array[start]]--;
                    start++;
                }
                duplicate[array[start]]--;
                start++;
            }
            if (maxLength < pointer - start + 1) maxLength = pointer - start + 1;
        }
        System.out.print(maxLength);
    }
}
