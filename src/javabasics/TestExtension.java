package javabasics;

public class TestExtension {
    public static void main(String[] args) {
        Dog dog = new Dog("小七");
        dog.eat();
        dog.bark();
        System.out.println(dog.name);

        Dog dog2 = new Dog("小狗");
        Cat cat  = new Cat("小猫");
        dog2.eat();
        cat.eat();
        dog2.makesound();
        cat.makesound();

        //抽象类不能进行实例化 不能new

    }
}
