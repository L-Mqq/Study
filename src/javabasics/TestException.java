package javabasics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestException {
    public static  void  readfile() throws  Exception{
        File file = new File("no.txt");
        FileInputStream inputStream = new FileInputStream(file);

    }
    public static void main(String[] args) {
       try {
           readfile();
           System.out.println("hello exception");  //ArithmeticException
       }catch (Exception e){
           System.out.println(e.getMessage());
       }finally {
           System.out.println("end");
       }
    }
}
