package javaGrammer;

import java.util.*;

public class listExample2 {
    public static void main(String[] args){
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        HashSet<List<String>> set = new HashSet<>();
        list1.add("A"); list2.add("A");
        System.out.println(list1==list2);
        System.out.println(list1.equals(list2));
        set.add(list1);
        System.out.println(set.contains(list2));
        System.out.println(list1.hashCode() == list2.hashCode());

        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{1, 2});
        System.out.println(list.contains(new Integer[]{1, 2}));
    }
}
