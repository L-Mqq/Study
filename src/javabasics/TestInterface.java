package javabasics;

import java.util.SortedMap;

public class TestInterface {
    public static void main(String[] args) {
        Speak object = new Bird();
        object.searchLanuage();

        Speak fish2 = new Fish();
        fish2.searchLanuage();
    }
}
