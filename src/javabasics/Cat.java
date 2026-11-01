package javabasics;

public class Cat extends Animal{
    public Cat(String name){
        super(name);
    }

    @Override
    public void eat(){
        System.out.println(name + "在吃罐头");
    }

    @Override
    public  void makesound(){
        System.out.println(name + "在喵喵叫");
    }

}
