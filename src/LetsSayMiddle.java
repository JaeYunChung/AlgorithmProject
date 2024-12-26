import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class LetsSayMiddle {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        List<Integer> buffer = new LinkedList<>();
        buffer.add(Integer.parseInt(br.readLine()));
        for(int n = 1 ; n < num; n++){
            binaryInsert(buffer, Integer.parseInt(br.readLine()), 0, n-1);
            System.out.println(buffer.get(n/2));
        }
    }
    public static void binaryInsert(List<Integer> list, int insertNumber, int start, int end)
    {
        int middle = (start + end)/2;
        if(insertNumber < list.get(start))
        {
            list.add(middle, insertNumber);
        }
        else if (list.get(end) < insertNumber)
        {
            list.add(middle+1, insertNumber);
        }
        else if(list.get(middle) <= insertNumber && insertNumber <= list.get(middle+1))
        {
            list.add(middle+1, insertNumber);
        }
        else if(list.get(middle) < insertNumber) binaryInsert(list, insertNumber, middle, end);
        else binaryInsert(list, insertNumber, start, middle);
    }
}
