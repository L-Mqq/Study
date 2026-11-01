package javabasics;

public class NumericContainer<T extends Number & Comparable<T>> {
    private T number;

    public NumericContainer(T number){
        this.number = number;
    }

    public T getNumber() {
        return number;
    }

    public T max(T a,T b) {
        return a.compareTo(b) >= 0? a:b;  //返回大的数
    }

    public static void main(String[] args) {
        NumericContainer<Integer> sales = new NumericContainer<>(20);
        System.out.println(sales.getNumber());

        NumericContainer<Double> height = new NumericContainer<Double>(183.5);
        System.out.println(height.getNumber());

        System.out.println(sales.max(20,30));
        System.out.println(height.max(13.4,55.6));

    }
}
