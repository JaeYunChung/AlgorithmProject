package heap;

public class MakeHeap {
    private static final int[] arr = {0, 3, 2, 10, 7, 6};
    public static void main(String[] args){

    }

    private void make_heap(int[] arr){
        int n = arr.length;
        Heap heap = new Heap(n);
        for (int i = 0; i < n; i++){
            heap.arr[i + 1] = arr[i];
        }
        for (int i = n/2; i >= 0; i--){
            bubble_down(heap, i);
        }
    }
    private void bubble_down(Heap heap, int n){

    }
    private void bubble_up(){

    }
    public static class Heap{
        int[] arr;
        int n;
        public Heap(int n){
            this.n = n;
            this.arr = new int[n + 1];
        }
    }
}
