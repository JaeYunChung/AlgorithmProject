package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class DiceGameProblem {
    public static void main(String[] args) throws IOException {
        int result = 100;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int L = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        List<Node> graph = new ArrayList<>();
        Map<Integer, Integer> ladders = new HashMap<>();
        Map<Integer, Integer> snakes = new HashMap<>();

        for (int i = 0; i < L; i++){
            st = new StringTokenizer(br.readLine(), " ");
            ladders.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0 ; i < S; i++){
            st = new StringTokenizer(br.readLine(), " ");
            snakes.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2)->o1.count - o2.count);
        queue.add(new Node(1, 1, 0));
        Set<Integer> visit = new HashSet<>();
        visit.add(1);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            int start = node.end;
            if (start == 100) {
                result = node.count;
                break;
            }
            if (ladders.containsKey(start)) {
                queue.add(new Node(start, ladders.get(start), node.count));
                visit.add(ladders.get(start));
            }
            else if (snakes.containsKey(start)){
                queue.add(new Node(start, snakes.get(start), node.count));
                visit.add(snakes.get(start));
            }
            else {
                for (int i = 1; i <= 6; i++) {
                    if (!visit.contains(start + i) && start + i <= 100) {
                        queue.add(new Node(start, start + i, node.count + 1));
                        visit.add(start + i);
                    }
                }
            }
        }
        System.out.print(result);
    }
    public static class Node{
        int start;
        int end;
        int count;
        public Node(int start, int end, int count){
            this.start = start;
            this.end = end;
            this.count = count;
        }
    }
}
