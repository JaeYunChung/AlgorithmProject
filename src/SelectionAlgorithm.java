import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SelectionAlgorithm {
    private static int[] arr = {1, 3, 10, 21, 9, 7, 15, 13, 19};
    public static void main(String[] args){
        System.out.println(select(arr, 4));
    }
    private static int select(int[] s, int k){
        int pivot = s[0];
        int[] s1 = new int[arr.length];
        int[] s2 = new int[arr.length];
        int[] s3 = new int[arr.length];
        int index1 = 0, index2 = 0, index3 = 0;
        for (int e : s){
            if (e == -1) break;
            if (e < pivot){
                s1[index1++] = e;
            }
            else if(e > pivot){
                s3[index3++] = e;
            }
            else{
                s2[index2++] = e;
            }
        }s1[index1] = -1;
        s2[index2] = -1;
        s3[index3] = -1;
        if(k <= index1) return select(s1, k);
        else if (index1 < k && k <= index1 + index2) return s2[0];
        else return select(s3, k - index1 - index2);
    }
}
