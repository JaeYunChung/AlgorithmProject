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
        Map<Integer, Set<Map.Entry<Integer, Integer>>> relationMap = new HashMap<>();
        Map<Integer, Set<Map.Entry<Integer, Integer>>> resultMap = new HashMap<>();
        for (int i = 0; i < numberOfVideo - 1; i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            relationMap.putIfAbsent(key, new HashSet<>());
            relationMap.get(key).add(new AbstractMap.SimpleEntry<>(value, distance));
            relationMap.putIfAbsent(value, new HashSet<>());
            relationMap.get(value).add(new AbstractMap.SimpleEntry<>(key, distance));
        }
        for (int key : relationMap.keySet())
        {
            Deque<Map.Entry<Integer, Integer>> stack = new ArrayDeque<>();
            Set<Map.Entry<Integer, Integer>> values = new HashSet<>();
            Set<Integer> find = new HashSet<>();
            find.add(key);
            stack.push(new AbstractMap.SimpleEntry<>(key, Integer.MAX_VALUE));
            while(!stack.isEmpty()) {
                Map.Entry<Integer, Integer> entry = stack.pop();
                find.add(entry.getKey());
                Set<Map.Entry<Integer, Integer>> entrySet = relationMap.get(entry.getKey());
                int value = entry.getValue();
                for (Map.Entry<Integer, Integer> each : entrySet) {
                    if(find.contains(each.getKey()))
                    {
                        continue;
                    }
                    if (value < each.getValue())
                    {
                        values.add(new AbstractMap.SimpleEntry<>(each.getKey(), value));
                    }
                    else values.add(each);
                    stack.push(each);
                }
            }
            resultMap.put(key, values);
        }
        for (int i = 0; i < numberOfQuestion; i++)
        {
            int count = 0;
            st = new StringTokenizer(br.readLine(), " ");
            int limit = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken());
            Set<Map.Entry<Integer, Integer>> entrySet = resultMap.get(node);
            for(Map.Entry<Integer, Integer> entry : entrySet)
            {
                if(entry.getValue() >= limit) count++;
            }
            System.out.println(count);
        }

    }
}
