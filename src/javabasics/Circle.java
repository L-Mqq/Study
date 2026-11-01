package javabasics;

//实现接口的类
public class Circle implements ColorShape{
    private double radios;
    private String color;

    @Override
    public double area(){
        return PI * radios * radios;
    }

    @Override
    public String getColor( ){
        return this.color;
    }

    @Override
    public void displayInfo(){
        System.out.println("这个一个" + color + "圆，面积为" + area());
    }

    public static void main(String[] args) {
        Circle c = new Circle();
        c.radios = 2;
        c.color = "紫色";
        c.displayInfo();
    }
}
