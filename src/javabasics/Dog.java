package javabasics;

public class Dog extends Animal{

    public Dog(String name){
        super(name);  //调用父类的构造方法
    }


    @Override  //方法重写
    public void eat(){
        System.out.println(name + "在啃骨头");
    }

    @Override
    public void makesound(){
        System.out.println(name + "在旺旺叫");
    }
    public void bark(){
        System.out.println(name + "在wangwang叫");
    }




}
