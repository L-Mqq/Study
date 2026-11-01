package javabasics;

public class TestPair<K,V> {
    private K key;
    private V value;

    public TestPair(K key,V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public static void main(String[] args) {
        TestPair<String,Integer> s1 = new TestPair<>("小明",100);
        System.out.println("名字:" + s1.getKey() + ";"+"成绩是：" + s1.getValue());

        TestPair<Integer,String> goods = new TestPair<>(100,"苹果");
        System.out.println(goods.getKey()+ "个" + goods.getValue());
    }
}
