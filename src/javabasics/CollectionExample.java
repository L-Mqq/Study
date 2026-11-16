package javabasics;

import java.util.*;
public class CollectionExample {
    public static void main(String[] args) {
        System.out.println("hello");

        Collection<String> collection = new ArrayList<>();
        collection.add("apple");
        collection.add("banana");
        collection.add("pear");

        //size
        System.out.println("集合大小：" + collection.size());

        //判断集合是否包含某个元素
        System.out.println(collection.contains("apple"));

        //遍历集合 iterator  for-each
        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        for (String str:collection){
            System.out.println(str);
        }

        //移除元素
        collection.remove("apple");
        System.out.println(collection.size());

        //clear
        collection.clear();
        System.out.println(collection.isEmpty());
    }
}
