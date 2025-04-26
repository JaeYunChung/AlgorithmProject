package sorting;

import java.util.Arrays;

public class QuickSortingAlgorithm {
    private static final int[] arr = {2, 3, 14, 7, 5, 8, 6, 10};
    public static void main(String[] args){
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    public static void quickSort(int[] arr, int start, int end){
        if (start >= end) return;
        int startIndex = start;
        int pivot = arr[end];
        int pivotIndex = end;
        end--;
        while(true){
            while(arr[start] < pivot){
                start++;
            }
            while(arr[end] > pivot){
                end--;
            }
            if(start < end) swap(arr, start, end);
            else {
                swap(arr, start, pivotIndex);
                break;
            }
        }
        quickSort(arr, startIndex, end);
        quickSort(arr, start + 1, pivotIndex - 1);
    }
    private static void swap(int[] arr, int index1, int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
