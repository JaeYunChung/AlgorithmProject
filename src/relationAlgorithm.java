import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class relationAlgorithm {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int numberOfVideo = Integer.parseInt(st.nextToken());
        int numberOfQuestion = Integer.parseInt(st.nextToken());
        Set<Node> nodeSet = new HashSet<>();
        Deque<Node> queue = new ArrayDeque<>();
        for (int i = 0; i < numberOfVideo - 1; i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int similarity = Integer.parseInt(st.nextToken());
            nodeSet.add(new Node(v1, v2, similarity));
            nodeSet.add(new Node(v2, v1, similarity));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfQuestion; i++){
            Set<Integer> find = new HashSet<>();
            st = new StringTokenizer(br.readLine(), " ");
            int limit = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            queue.offer(new Node(start, start, Integer.MAX_VALUE));
            int count = 0;
            while(!queue.isEmpty())
            {
                Node entry = queue.poll();
                find.add(entry.dest);
                Set<Node> selectedSet = nodeSet.stream()
                        .filter(node -> node.getVertex(entry.dest))
                        .collect(Collectors.toSet());
                for (Node e : selectedSet)
                {
                    if(find.contains(e.dest)) continue;
                    Node newNode;
                    if (entry.similarity < e.similarity)
                    {
                        newNode = new Node(start, e.dest, entry.similarity);
                    }
                    else newNode = new Node(start, e.dest, e.similarity);
                    if(newNode.similarity >= limit) count++;
                    queue.offer(newNode);
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
    public static class Node
    {
        int src;
        int dest;
        int similarity;
        public Node(int src, int dest, int similarity)
        {
            this.src = src;
            this.dest = dest;
            this.similarity = similarity;
        }
        public boolean getVertex(int dest)
        {
            return this.src == dest;
        }
        public void setSimilarity(int similarity)
        {
            this.similarity = similarity;
        }
    }
}
