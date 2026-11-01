package javabasics;

public class TestThrow {
    public static int divide(int a ,int b) throws  Exception{ //也可以先用throws抛出异常之后再进行捕获
//        if (b == 0){
//            throw  new ArithmeticException("除数不为0");
//        }
        return a/b;
    }

    public static void main(String[] args) {
        try {
            divide(10,0);
            System.out.println("hello");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("OK");
        }
    }
}
