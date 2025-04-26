package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LowestCostProblem {
    public static void main(String[]  args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int dest = Integer.parseInt(st.nextToken());
        int numOfPath = Integer.parseInt(st.nextToken());;
        Map<Integer, List<Integer[]>> graph = new HashMap<>();
        PriorityQueue<Position> pq = new PriorityQueue<>();
        Set<Integer> visit = new HashSet<>();
        for (int i = 0; i < numOfPath; i++)
        {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.putIfAbsent(node1, new ArrayList<>());
            graph.putIfAbsent(node2, new ArrayList<>());
            graph.get(node1).add(new Integer[]{node2, cost});
            graph.get(node2).add(new Integer[]{node1, cost});
        }
        pq.add(new Position(1, 0));
        while(!pq.isEmpty())
        {
            Position p = pq.poll();
            visit.add(p.loc);
            if (p.loc == dest)
            {
                System.out.print(p.cost);
                break;
            }
            for (Integer[] dests : graph.get(p.loc))
            {
                if(visit.contains(dests[0])) continue;
                Position next = new Position(dests[0], p.cost + dests[1]);
                pq.add(next);
            }
        }
    }
    public static class Position implements Comparable{
        int loc;
        int cost;

        public Position(int loc, int cost){
            this.loc = loc;
            this.cost = cost;
        }
        @Override
        public int compareTo(Object o) {
            Position p = (Position) o;
            return cost - p.cost;
        }
    }
}
