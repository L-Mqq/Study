package javabasics;

public class GenericContainer<T> {
    private T data;
    public T getData(){
        return data;
    }
    public void setData( T data){
        this.data = data;
    }

    public static void main(String[] args) {
        GenericContainer<String> stringContainer = new GenericContainer<>();
        stringContainer.setData("apple");
        System.out.println(stringContainer.getData());

        GenericContainer<Integer> intContainer = new GenericContainer<>();
        intContainer.setData(2);
        System.out.println(intContainer.getData());

    }


}
