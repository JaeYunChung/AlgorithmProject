package javaGrammer;

import java.util.*;

public class assistClass {
    public static void main(String[] args) {
        String[] arr = new String[]{"A", "B", "C"};
        List<String> fixedList = Arrays.asList(arr);
        try {
            fixedList.add("D");
        } catch (Exception e) {
            throw new IllegalArgumentException("새로운 원소를 추가할 수 없습니다.");
        }
        finally {
            List<String> list = new ArrayList<>(fixedList);
            list.add("D");
            System.out.println(list);
            //List<String> streamList = Arrays.stream(arr).collect(Collectors.toUnmodifiableList())
            // 불변 객체로 변환될 수 밖에 없음;
            //streamList.add("D");
            //System.out.println(streamList);
        }
    }

}
