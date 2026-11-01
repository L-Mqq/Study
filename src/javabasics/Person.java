package javabasics;

public class Person {
    //person的属性
    private String name;
    int age;

    @Override
    public  String  toString(){
        return "Person:" + name + "age:"+age;
    }

    //构造函数
    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }
    public int getAge(){
        return  age;
    }


    public  void  setName(String name){
        this.name = name;
    }

}
