import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class LetsSayMiddle {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1,o2)->o2-o1);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2)->o1-o2);
        int i=0;
        for (; i < num ; i=i+1)
        {
            int entry = Integer.parseInt(br.readLine());

            //중앙값보다 크면 minHeap, 작으면 maxHeap으로
            if(!maxHeap.isEmpty())
            {
                if(maxHeap.peek() > entry) maxHeap.add(entry);
                else minHeap.add(entry);
            }
            else maxHeap.add(entry);

            // maxHeap 크기는 minHeap크기와 최대 1차이를 허용한다.
            if(maxHeap.size() > minHeap.size() + 1) minHeap.add(maxHeap.poll());
            else if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.poll());
            }

            System.out.println(maxHeap.peek());

        }
    }

}
