package javabasics;

import java.util.ArrayList;
import java.util.List;

public class Container<T>{
    private T data;

    public Container(){
        //data = new T(); 是错误的！！
        //但可以先new object再转化为T
        data = (T) new Object();
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
    }
}
