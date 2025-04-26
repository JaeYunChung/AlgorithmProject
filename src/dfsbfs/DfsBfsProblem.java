package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DfsBfsProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int numOfVertex = Integer.parseInt(st.nextToken());
        int numOfEdge = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());
        Map<Integer, Node> graph = new HashMap<>();
        for (int i = 0; i < numOfVertex; i++)
        {
            graph.put(i + 1, new Node(i + 1));
        }
        for (int i = 0; i < numOfEdge; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            Integer n1 = Integer.parseInt(st.nextToken());
            Integer n2 = Integer.parseInt(st.nextToken());
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }
        List<Integer> dfsList = dfs(graph, startNode, new ArrayList<>());
        List<Integer> bfsList = bfs(graph, startNode);
        dfsList.forEach(each -> System.out.print(each + " "));
        System.out.println();
        bfsList.forEach(each -> System.out.print(each + " "));
    }
    public static List<Integer> dfs(Map<Integer, Node> graph, Integer startNode, List<Integer> find) {
        Node start = graph.get(startNode);
        find.add(startNode);
        PriorityQueue<Integer> neighbors = start.neighbors;
        PriorityQueue<Integer> buffer = new PriorityQueue<>();
        while(!neighbors.isEmpty()) {
            Node nextNode = graph.get(neighbors.poll());
            if (!find.contains(nextNode.node)) {
                dfs(graph, nextNode.node, find);
            }
            buffer.add(nextNode.node);
        }
        start.neighbors = buffer;
        return find;
    }

    public static List<Integer> bfs(Map<Integer, Node> graph, Integer startNode)
    {
        Node start = graph.get(startNode);
        Deque<Node> queue = new ArrayDeque<>();
        List<Integer> find = new ArrayList<>();
        queue.add(start);
        while(!queue.isEmpty())
        {
            Node node = queue.poll();
            if (!find.contains(node.node)) {
                find.add(node.node);
                for (Integer e : node.neighbors) {
                    if (!find.contains(e)) {
                        queue.add(graph.get(e));
                    }
                }
            }
        }return find;
    }
    public static class Node{
        public Integer node;
        public PriorityQueue<Integer> neighbors;

        public Node(Integer node) {
            this.node = node;
            neighbors = new PriorityQueue<>();
        }

        public void add(Integer n){
            neighbors.add(n);
        }
    }
}
