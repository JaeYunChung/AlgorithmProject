public class PolynomialMatchingOtherSolution {
    static int max = Integer.MIN_VALUE;
    static int[][] arr;
    static boolean[][] visit;

    // 상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int n;
    static int m;
    static int temp;
    static int fcount = 0;

    public int find(int row, int column, int[][] map, int value) throws NumberFormatException {

        arr = map;
        n = row;
        m = column;
        visit = new boolean[n][m];
        max = Integer.MIN_VALUE;
        temp = value;

        // 전체 탐색 (dfs)
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                visit[i][j] = true;
                solve(i,j,arr[i][j],1);
                visit[i][j] = false;
            }
        }

        return max;
    }

    static void solve(int row, int col, int sum, int count) {



        // 테트로미노 완성 시 수들의 합 계산
        if(count == 4) {
            if(sum > max) {
                max = sum;
            }
            return;
        }

        // 상하좌우 탐색
        for(int i = 0; i < 4; i++) {
            int curRow = row + dx[i];
            int curCol = col + dy[i];

            // 범위 벗어나면 예외 처리
            if(curRow < 0 || curRow >= n || curCol < 0 || curCol >= m) {
                continue;
            }

            // 아직 방문하지 않은 곳이라면
            if(!visit[curRow][curCol]) {

                // 보라색(ㅗ) 테트로미노 만들기 위해 2번째 칸에서 탐색 한번 더 진행
                if(count == 2) {
                    visit[curRow][curCol] = true;
                    solve(row, col, sum + arr[curRow][curCol], count + 1);
                    visit[curRow][curCol] = false;
                }

                visit[curRow][curCol] = true;
                solve(curRow, curCol, sum + arr[curRow][curCol], count + 1);
                visit[curRow][curCol] = false;
            }
        }
    }


}
