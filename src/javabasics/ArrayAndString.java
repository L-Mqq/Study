package javabasics;

import java.util.Arrays;

public class ArrayAndString {
    public static void main(String[] args) {
        int[] numbers = {1,88,100,67,77};
        int size = numbers.length;
        System.out.println(size);

        //遍历数组
        for (int i = 0;i < numbers.length;i++){
            System.out.println(numbers[i]);
        }

        //对数组进行排序
        Arrays.sort(numbers);
        for ( int number :numbers){
            System.out.println(number);
        }

        //String
        String a = "hello,world";
        System.out.println(a);

        //charAt
        char str = a.charAt(5);
        System.out.println(str);

        //concat
        String s = "!!";
        String result = a + s;
        System.out.println(result);

        String ConcatResult = a.concat(s);
        System.out.println(ConcatResult);

        //equals
        System.out.println(result.equals(ConcatResult));

        //length
        String test = "cat";
        int stringSize = test.length();
        System.out.println(stringSize);

        //substring
        String testStr = "Hello";
        System.out.println(testStr.length());
        String sub = testStr.substring(2,4); //左闭右开
        System.out.println(sub);

    }
}
