package javabasics;

public class Test {
    public static void main(String[] args) {
        Person p = new Person("Lee",19);
        System.out.println(p.getName());
        System.out.println(p.getAge());
        System.out.println(p.age);
        System.out.println(p.toString());
    }
}
