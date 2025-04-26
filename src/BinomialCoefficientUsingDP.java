public class BinomialCoefficientUsingDP {
    public static void main(String[] args){
        int n = 5, k = 3;
        System.out.println(bin(n, k));
    }
    public static int bin(int n, int k){
        int[] arr = new int[n + 1];
        int temp, pre;
        for (int i = 0; i <= n; i++){
            pre = 1;
            for (int j = 0; j <= Math.min(i, k); j++){
                if (j == 0 || j == i) arr[j] = 1;
                else {
                    temp = pre + arr[j];
                    pre = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr[k];
    }
}
