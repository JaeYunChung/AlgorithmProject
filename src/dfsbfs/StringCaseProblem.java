package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringCaseProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        if(dfs(new StringBuilder(br.readLine()), S))System.out.print(1);
        else System.out.print(0);
    }
    public static boolean dfs(StringBuilder sb, String S){
        if(sb.toString().equals(S)) return true;
        if (sb.length() == S.length()) return false;
        if(sb.charAt(sb.length() - 1) == 'A') {
            sb.deleteCharAt(sb.length() - 1);
            if(dfs(sb, S)) return true;
            sb.append("A");
        }
        if (sb.charAt(0) == 'B'){
            sb.deleteCharAt(0);
            sb.reverse();
            if (dfs(sb, S)) return true;
            sb.reverse();
            sb.insert(0, 'B');
        }
        return false;
    }

}
