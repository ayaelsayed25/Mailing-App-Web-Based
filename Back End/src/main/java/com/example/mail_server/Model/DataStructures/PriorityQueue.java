package com.example.mail_server.Model.DataStructures;

import java.util.LinkedList;

public class PriorityQueue<E> implements IPriorityQueue<E> {
    private Node front;
    private Node rear;
    private int size;

    private class Node {
        E element;
        int priority;
        Node next;
        Node prev;
    }

    public PriorityQueue() {
        size = 0;
        front = null;
        rear = null;
    }

    @Override
    public void insert(E item, int key) {
        if (key > 0) {
            Node node = new Node();
            node.element = item;
            node.priority = key;
            if (front == null) {
                front = node;
                rear = node;
                node.next = null;
            } else {
                if (key < front.priority) {
                    node.next = front;
                    front.prev = node.next;
                    front = node;
                } else if (key >= rear.priority) {
                    node.next = null;
                    rear.next = node;
                    node.prev = rear;
                    rear = node;
                } else {
                    Node start = front.next;
                    while (start.priority < key)
                        start = start.next;
                    node.next = start;
                    node.prev = start.prev;
                    start.prev.next = node;
                    start.prev = node;
                }
            }
            size++;
        } else {
            throw new RuntimeException("Invalid key!!, Key must be 1 or higher");
        }
    }

    @Override
    public E removeMin() {
        if (!isEmpty()) {
            Node temp = front;
            E res = temp.element;
            front = front.next;
            if (front == null)
                rear = null;
            size--;
            return res;
        } else {
            throw new RuntimeException("Empty priority queue!!");
        }
    }

    @Override
    public E min() {
        if (!isEmpty())
            return front.element;
        else {
            throw new RuntimeException("Empty priority queue!!");
        }
    }

    @Override
    public boolean isEmpty() {
        return (front == null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public LinkedList<E> toList() {
        LinkedList<E> priorityList = new LinkedList<>();
        while (!this.isEmpty())
        priorityList.add(this.removeMin());
        return priorityList;
    }

}
