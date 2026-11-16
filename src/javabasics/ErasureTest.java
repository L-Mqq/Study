package javabasics;

public class ErasureTest {
    public static void main(String[] args) {
        GenericContainer<String> strContainer = new GenericContainer<>();
        GenericContainer<Integer> intContainer = new GenericContainer<>();

        System.out.println(strContainer.getClass());
        System.out.println(intContainer.getClass());

        //GenericContainer<要引用数据类型>

        System.out.println(strContainer.getClass().equals(intContainer.getClass()));  //说明他们的父类是一样的
    }
}
