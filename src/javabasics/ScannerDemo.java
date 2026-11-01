package javabasics;

import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        System.out.println("请输入姓名：");
//        String name = scanner.next();
//
//        System.out.println("请输入年龄：");
//        int age = scanner.nextInt();
//
//        System.out.println("请输入身高：");
//        double height = scanner.nextDouble();
//
//        System.out.println("你是" + name + "年龄：" + age + "身高" + height);

//        System.out.print("fullname:");
//        String name = scanner.nextLine();  //读取整行，包括空格 可以消耗/n

//        System.out.println("请输入一组数:");
//        while (scanner.hasNext()){
//            int num = scanner.nextInt();
//            System.out.println(num);
//        }

        System.out.println("请输入一组数:");
        scanner.useDelimiter(",");
        while (scanner.hasNext()){
            int num = scanner.nextInt();
            System.out.println(num);
        }





    }
}
