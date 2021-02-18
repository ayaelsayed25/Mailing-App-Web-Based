package com.example.mail_server.Model.DataStructures;

import java.util.LinkedList;

public interface IPriorityQueue<E> {
    /**
     * Inserts an item with priority key. key "1" is the highest priority.
     */
    void insert(E item, int key);

    /**
     * Removes the object with the highest priority.
     */
    E removeMin();

    /**
     * Return the object with the highest priority.
     */
    E min();

    /**
     * Tests if this queue is empty.
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the queue
     */
    int size();

    LinkedList<E> toList();
}