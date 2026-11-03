package Work;

public class TestDynamicArray {
    public static void main(String[] args) {
       try {
           DynamicArray<String> strArray = new DynamicArray<>("A");
           strArray.getArray();

           System.out.println("");
           strArray.add("a");
           strArray.add("B");
           strArray.add("C");
           strArray.add("D");
           strArray.add("E");
           strArray.add("F");
           strArray.add("G");
           strArray.add("H");
           strArray.add("I"); //10个
           System.out.println( strArray.getSize()); //10
           System.out.println(strArray.getCapacity()); //10

           System.out.println("----------");
           strArray.add("M");
           System.out.println( strArray.getSize()); //11
           System.out.println(strArray.getCapacity()); //20
           strArray.getArray();

           System.out.println("-----------");
           System.out.println(strArray.get(2)); //B
           strArray.update(2,"K"); //把 B 改为 K
           System.out.println(strArray.get(2)); //K
           System.out.println(strArray.getSize()); //11

           System.out.println(strArray.remove(2)); //K 把K删除了
           System.out.println(strArray.getSize()); //10

           System.out.println("");
           System.out.println(strArray.get(20));
           System.out.println(strArray.remove(20));
           strArray.update(20,"p");

       }catch (Exception e){
           System.out.println(e.getMessage());
       }


       try {
            DynamicArray<Integer> intArray = new DynamicArray<>(1);


            intArray.add(2);
            intArray.add(3);
            intArray.add(4);
            intArray.add(5);
            intArray.add(6);
            intArray.add(7);
            intArray.add(8);
            intArray.add(9);
            intArray.add(0);
            intArray.getArray();
            System.out.println(" ");
            System.out.println(intArray.getSize()); //10
            System.out.println(intArray.getCapacity()); //10

           System.out.println("--------------");
           intArray.add(0);
           System.out.println(intArray.getSize()); //11
           System.out.println(intArray.getCapacity()); //20

           System.out.println(intArray.get(2)); //3
           intArray.update(2,0);
           System.out.println(intArray.get(2));

           intArray.getArray();
           System.out.println("");
           System.out.println(intArray.getSize()); //11
           System.out.println( intArray.remove(0)); //移除第一个
           System.out.println(intArray.getSize()); //10
           intArray.getArray();

           System.out.println("");
           System.out.println(intArray.get(20));
           System.out.println(intArray.remove(20));
           intArray.update(20,9);



       }catch (Exception e){
           System.out.println(e.getMessage());
       }
    }
}
