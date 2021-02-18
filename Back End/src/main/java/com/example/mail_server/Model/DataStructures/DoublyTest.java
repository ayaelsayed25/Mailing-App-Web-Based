package com.example.mail_server.Model.DataStructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoublyTest {
    public DoublyTest() {
    }

    @Test
    void test() {
        linkedlist p = new linkedlist();
        p.add(3);
        p.add(2);
        p.add(1);
        p.add(0);


//        new linkedlist();
//        p.add(0, 10);
//        p.add(20);
//        p.add(2, 30);
//        p.add(40);
//        p.add(50);
//        p.add(5, 60);
//        Assertions.assertThrows(RuntimeException.class, () -> {
//            p.add(-1, 100);
//        }, "Invalid Index !");
//        Assertions.assertThrows(RuntimeException.class, () -> {
//            p.add(-2, 200);
//        }, "Invalid Index !");
//        Assertions.assertEquals(10, p.get(0));
//        Assertions.assertEquals(20, p.get(1));
//        Assertions.assertEquals(30, p.get(2));
//        Assertions.assertEquals(40, p.get(3));
//        Assertions.assertEquals(50, p.get(4));
//        Assertions.assertEquals(60, p.get(5));
//        Assertions.assertThrows(RuntimeException.class, () -> {
//            p.get(-1);
//        }, "Invalid Index !");
//        Assertions.assertThrows(RuntimeException.class, () -> {
//            p.get(-2);
//        }, "Invalid Index !");
//        p.add(3, 35);
//        Assertions.assertEquals(35, p.get(3));
//        p.set(1, 25);
//        Assertions.assertEquals(25, p.get(1));
        linkedlist sublist = p.sublistCopy(1, 3);
        Assertions.assertEquals(2, sublist.get(0));
        Assertions.assertEquals(1, sublist.get(1));
        Assertions.assertEquals(0, sublist.get(2));
        Assertions.assertEquals(3, sublist.size());
//        Assertions.assertEquals(40, p.get(4));
//        Assertions.assertEquals(50, p.get(5));
//        Assertions.assertEquals(60, p.get(6));
//        p.remove(4);
//        Assertions.assertEquals(6, p.size());
//        Assertions.assertEquals(50, p.get(4));
//        Assertions.assertFalse(p.contains(150));
//        Assertions.assertTrue(p.contains(60));
//        p.clear();
//        Assertions.assertTrue(p.isEmpty());
    }
}

