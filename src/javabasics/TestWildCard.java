package javabasics;

import javax.print.attribute.standard.NumberUp;

public class TestWildCard {

    public static void printContainer1(GenericContainer<?> container){
        System.out.println("数据是：" + container.getData());
    }
    public static void printContainer2(GenericContainer<? extends Number> container){
        System.out.println("数据是：" + container.getData());
    }

    //规定下界为Integer(包装 int 的对象)
    public static void printContainer3(GenericContainer<? super Integer > container){
        System.out.println("数据是：" + container.getData());
    }

    public static void showNumber(GenericContainer<? super Integer>container){
        container.setData(20);
        System.out.println("the data is" + container.getData());
    }
    public static void main(String[] args) {
        GenericContainer<String> strContainer = new GenericContainer<>();
        strContainer.setData("apple");
        printContainer1(strContainer);


         GenericContainer<Integer> intContainer = new GenericContainer<>();
         intContainer.setData(20);
         printContainer2(intContainer);

         GenericContainer<Number> numContainer = new GenericContainer<>();
         showNumber(numContainer);

         GenericContainer<Object> objContainer= new GenericContainer<>();
         showNumber(objContainer);
    }
}
