package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class relationAlgorithm {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int numberOfVideo = Integer.parseInt(st.nextToken());
        int numberOfQuestion = Integer.parseInt(st.nextToken());
        Deque<Node> queue = new ArrayDeque<>();
        List<Node>[] arr = new List[numberOfVideo + 1];
        for (int i = 1; i < numberOfVideo +1 ; i ++)
        {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < numberOfVideo - 1; i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int similarity = Integer.parseInt(st.nextToken());
            arr[v1].add(new Node(v2, similarity));
            arr[v2].add(new Node(v1, similarity));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfQuestion; i++){
            Set<Integer> find = new HashSet<>();
            st = new StringTokenizer(br.readLine(), " ");
            int limit = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            queue.offer(new Node(start, Integer.MAX_VALUE));
            int count = 0;
            while(!queue.isEmpty())
            {
                Node entry = queue.poll();
                find.add(entry.dest);
                for (Node e : arr[entry.dest])
                {
                    if(find.contains(e.dest)) continue;
                    Node newNode;
                    if (entry.similarity < e.similarity)
                    {
                        newNode = new Node( e.dest, entry.similarity);
                    }
                    else newNode = new Node(e.dest, e.similarity);
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
        int dest;
        int similarity;
        public Node(int dest, int similarity)
        {
            this.dest = dest;
            this.similarity = similarity;
        }
    }
}
