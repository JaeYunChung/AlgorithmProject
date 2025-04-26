package sorting;

import java.util.Arrays;

public class MergeSortAlgoPrac {
    private static final int[] sortArr = {3, 2, 4, 1, 5, 2};
    public static void main(String[] args){
        int[] sortedArr = mergeSort(sortArr, 0, sortArr.length - 1);
        System.out.println(Arrays.toString(sortedArr));
    }
    public static int[] mergeSort(int[] arr, int start, int end){
        int middle = (start + end) / 2;
        if(start == end) return new int[]{arr[start]};
        int[] firstHalf = mergeSort(arr, start, middle);
        int[] lastHalf = mergeSort(arr, middle + 1, end);

        int firstSize = firstHalf.length;
        int lastSize = lastHalf.length;
        int[] sortedArr = new int[firstSize + lastSize];
        int i = 0, j = 0;
        for (i = 0; i < firstSize; i++){
            while(j < lastSize && firstHalf[i] > lastHalf[j]){
                sortedArr[i + j] = lastHalf[j];
                j++;
            }
            sortedArr[i + j] = firstHalf[i];
        }
        while(j < lastSize){
            sortedArr[i + j] = lastHalf[j];
            j++;
        }
        return sortedArr;
    }
}
