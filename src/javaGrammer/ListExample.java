package javaGrammer;

import java.util.ArrayList;
import java.util.List;

public class ListExample {
    public static void main(String[] args){
        List<String> list  = new ArrayList<>();
        list.add("A");
        System.out.println(list.toString().equals("A"));
        System.out.println(list.toString().substring(1, list.size() + 1).equals("A"));
    }
}
