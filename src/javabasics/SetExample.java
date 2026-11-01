package javabasics;

import java.util.HashSet;
import java.util.Set;

public class SetExample {
    public static void main(String[] args) {
        System.out.println("hello set");

        Set<Integer> set = new HashSet<>();  //Hashset 无重复元素  元素无序性

        set.add(1);
        set.add(4);
        System.out.println(set.size());
        set.add(4);
        System.out.println(set.size());
        set.add(55);
        set.add(6);
        set.add(7);
        set.add(100);

        System.out.println("---------");
        for (int num :set){
            System.out.println(num);
        }

    }
}
