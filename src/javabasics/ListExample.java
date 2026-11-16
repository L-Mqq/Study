package javabasics;

import java.util.ArrayList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        System.out.println("hello");

        List<Integer> list = new ArrayList<>();

        //添加元素
        list.add(2);
        list.add(4);
        list.add(6);  // 2 4 6
        System.out.println(list.get(0));
        System.out.println(list.get(2));
        System.out.println(list.size());

        list.add(5);  // 2 4 6 5
        System.out.println(list.size());

        //删除元素
        System.out.println("-----------");
        list.remove(0);  //4 6 5
        //遍历元素
        for (int num:list){
            System.out.println(num);
        }
        System.out.println("-----------");
        for (int i= 0;i<list.size();i++){
            System.out.println(list.get(i));
        }



    }
}
