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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfQuestion; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int limit = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken());
            Map<Integer, Integer> result = dfs(relationMap, Integer.MAX_VALUE, node, new HashMap<>());
            result.remove(node);
            int num = (int) result.values().stream().filter(each -> (each >= limit) ).count();
            sb.append(num).append("\n");
        }
        System.out.println(sb);
    }
    public static Map<Integer, Integer> dfs(Map<Integer, Set<Map.Entry<Integer, Integer>>> relationMap, int minValue, int key, Map<Integer, Integer> result)
    {
        Set<Map.Entry<Integer, Integer>> linkedNode = relationMap.get(key);
        for (Map.Entry<Integer, Integer> entry : linkedNode)
        {
            if(result.containsKey(entry.getKey())) continue;
            if (entry.getValue() < minValue) {
                result.put(entry.getKey(), entry.getValue());
                dfs(relationMap, entry.getValue(), entry.getKey(), result);
            }
            else {
                result.put(entry.getKey(), minValue);
                dfs(relationMap, minValue, entry.getKey(), result);
            }
        }
        return result;
    }
}
