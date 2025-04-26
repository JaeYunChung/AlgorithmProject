package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class ShortestPathProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int numOfPath = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> n1.start - n2.start);
        for (int i = 0; i < numOfPath; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            if (end > dest || end - start - length < 0) continue;
            queue.add(new Node(start, end, length));
        }
        Set<Node> set = new HashSet<>();
        while(!queue.isEmpty()){
            Node node = queue.poll();

            set.add(node);
        }
        int result = 0;
        for (Node entry : set){
            result += entry.length;
            dest -= entry.end - entry.start;
        }
        System.out.print(result + dest);
    }

    public static class Node{
        int start;
        int end;
        int length ;
        int profit;
        public Node(int start, int end, int length){
            this.start = start;
            this.end = end;
            this.length = length;
            this.profit = end - start - length;
        }
    }
}
