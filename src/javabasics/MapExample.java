package javabasics;

import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        System.out.println("hello");

        Map<String,Integer> map = new HashMap<>();

        map.put("apple",2);
        map.put("banana",4);
        map.put("pear",7);

        System.out.println(map.size());
        System.out.println(map.get("apple"));

        //遍历 keyset 拿出map中所有key
        for (String key:map.keySet()){
            System.out.println("key:" + key + ";"+"value:" + map.get(key) );
        }

        System.out.println("---------------");
        //拿出所以key value
        for (Map.Entry<String,Integer> entry : map.entrySet()){
            System.out.println("key:" + entry.getKey() + ";"+"value:" + entry.getValue());
        }

    }
}
