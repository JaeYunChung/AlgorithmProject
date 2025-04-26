import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class makeLineProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        List<Integer> line = new LinkedList<>();
        List<Integer> order = new ArrayList<>();
        while(st.hasMoreTokens()){
            order.add(0, Integer.parseInt(st.nextToken()));
        }
        for (Integer e: order){
            line.add(e, N);
            N -= 1;
        }
        line.forEach(each -> System.out.print(each + " "));
    }
}
