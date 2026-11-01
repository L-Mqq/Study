package javabasics;

public abstract class Animal {
    String name;

    //构造方法
    public Animal(String name) {
        this.name = name;
        //System.out.println("被调用了");
    }

    public void eat() {
        System.out.println(name + "在吃东西");
    }

    public abstract void makesound();
}
