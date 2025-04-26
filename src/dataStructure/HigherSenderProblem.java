package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HigherSenderProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Long[] tower = Arrays.stream(br.readLine().split(" "))
                .map(Long::parseLong)
                .toArray(Long[]::new);
        List<Entry> list = new ArrayList<>();
        list.add(new Entry(Long.MAX_VALUE, -1));
        for (int i = 0 ;i < N; i++){
            list.add(new Entry(tower[i], i));
        }
        Deque<Entry> stack = new ArrayDeque<>();
        int[] result = new int[N];
        for (Entry entry : list)
        {
            while(!stack.isEmpty())
            {
                if (stack.peek().height < entry.height)
                {
                    stack.pop();
                }
                else
                {
                    Entry acceptor = stack.peek();
                    result[entry.index] = acceptor.index + 1;
                    break;
                }
            }
            stack.push(entry);
        }
        for (int i = 0; i < result.length; i++){
            System.out.print(result[i] + " ");
        }
    }
    public static class Entry{
        long height;
        int index;
        Entry(long height, int index){
            this.height = height;
            this.index = index;
        }
    }
}
