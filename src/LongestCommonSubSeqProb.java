public class LongestCommonSubSeqProb {
    private static final String str1 = "entropy";
    private static final String str2 = "thorny";
    public static void main(String[] args){
        int[][] M = new int[str1.length() + 1][str2.length() + 1];
        int[][][] B = new int[str1.length() + 1][str2.length() + 1][2];
        lcsAlg(M, B);
    }
    public static void lcsAlg(int[][] M, int[][][] B){
        for (int j = 0; j < M[0].length; j++){
            M[0][j] = 0;
            B[0][j][0] = 0; B[0][j][1] = 0;
            // 0번째 글자에는 매칭되는 글자가 존재하지 않는다.
        }
        for (int i = 1; i < M.length; i++){
            M[i][0] = 0; B[i][0][0] = 0; B[i][0][1] = 0;
            for (int j = 1; j < M[0].length; j++){
                if (str1.charAt(i - 1) == str2.charAt(j - 1)){
                    // 만약 매칭되는 문자가 존재한다면
                    // 매칭되는 문자의 위치를 저장하고
                    // 매칭되는 전의 매칭 수에서 1을 증가시킨다.
                    M[i][j] = M[i - 1][j - 1] + 1;
                    B[i][j][0] = i;
                    B[i][j][1] = j;
                }
                else if (M[i - 1][j] > M[i][j - 1]){
                    M[i][j] = M[i - 1][j];
                    B[i][j][0] = B[i - 1][j][0];
                    B[i][j][1] = B[i - 1][j][1];
                }
                else{
                    M[i][j] = M[i][j - 1];
                    B[i][j][0] = B[i][j - 1][0];
                    B[i][j][1] = B[i][j - 1][1];
                }
            }
        }
        int i = M.length - 1, j = M[0].length - 1;
        while(M[i][j] > 0){
            System.out.print(str1.charAt(B[i][j][0] - 1));
            int temp1 = B[i][j][0] - 1;
            int temp2 = B[i][j][1] - 1;
            i = temp1; j = temp2;
        }
    }
}
