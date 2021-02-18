package com.example.mail_server.Model.DataStructures;

import java.util.LinkedList;
import java.util.List;

public class linkedlist<E> {

    private doubleNode<E> head;
    private doubleNode<E> tail;
    private int size;
    public linkedlist() {
        size = 0;
        head = new doubleNode<E>(null, null, null);
        tail = new doubleNode<E>(null, null, head);
        head.next=tail;
    }


    private static class doubleNode<E> {
        E element;
        doubleNode<E> next;
        doubleNode<E> prev;

        public doubleNode(E element, doubleNode<E> next, doubleNode<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private class iterator implements ListIterator<E> {
        private linkedlist.doubleNode<E> next;
        private linkedlist.doubleNode<E> previous;
        int currentIndex ;
        int fromIndex ;
        public iterator(int from){
            if(from == linkedlist.this.size){
                next = linkedlist.this.tail.prev;
            }else{
                next = getNode(from);
            }
            if(from == 0)
                previous = null;
            else previous = next.prev;
            currentIndex = fromIndex = from;
        }
        public boolean hasNext(){
            return currentIndex < size;
        }
        public E next(){
            if(!hasNext())
                throw new IllegalStateException();
            this.currentIndex ++;
            E nextElement = next.element;
            linkedlist.doubleNode<E> temp = next;
            next = next.next;
            previous = temp;
            return nextElement;
        }
        //        public E current(){
//            return current.element;
//        }
        public E previous(){
            if(!hasPrevious())
                throw new IllegalStateException();
            this.currentIndex --;
            E previousElement = previous.element;
            next = previous;
            previous = previous.prev;
            return previousElement;
        }
        public boolean hasPrevious() {
            return this.currentIndex > 0;
        }
        public void set(E e){
            if (this.next == null) {
                throw new IllegalStateException();
            } else {
                this.next.element = e;
            }
        }
//        private void checkIteratorIndex(){
//            if(this.currentIndex < fromIndex||this.currentIndex > size)
//                throw new IllegalStateException();
//        }
    }
    public ListIterator<E> listIterator(int index) {
        checkIndex(index);
        return new linkedlist<E>.iterator(index);
    }
    public void add(int index, E element) {
        doubleNode<E> current = head.next;
        if (index>size || index <0)
            throw new RuntimeException("Invalid Index !");
        else if (size == index)
            add(element);
        else {
            for (int i = 0; i < index; i++)
                current = current.next;
            doubleNode<E> addedNode = new doubleNode<>(element, current, current.prev);
            current.prev.next = addedNode;
            current.prev = addedNode;//it was current.next.prev >> error
            size++;
        }
    }

    public void add(E element) {
        doubleNode<E> addedNode = new doubleNode<>(element, tail, tail.prev); // three steps
        tail.prev.next = addedNode;
        tail.prev = addedNode;//You forgot this step..
        size++;
    }

    public E get(int index) {
        checkIndex(index);
        doubleNode<E> current;
        if(index<size/2) {
            current = head.next;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }
        else{
            current = tail.prev;
            for (int i = size-1; i > index; i--) {
                current = current.prev;
            }
        }
        return current.element;
    }

    private doubleNode<E> getNode(int index) {
        checkIndex(index);
        doubleNode<E> current;
        if(index<size/2) {
            current = head.next;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }
        else{
            current = tail.prev;
            for (int i = size-1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    public void set(int index, E element) {
        checkIndex(index);
        doubleNode<E> current ;
        if(index<size/2) {
            current = head.next;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }
        else{
            current = tail.prev;
            for (int i = size-1; i > index; i--) {
                current = current.prev;
            }
        }
        current.element = element;

    }


    public void clear() {
        head.next= tail;
        tail.prev = head;
        size = 0;
    }


    public boolean isEmpty() {

        return (size==0);
    }


    public void remove(int index) { // revise if you want to decide from where to start the loop depending of the index (nearer to tail or head)
        checkIndex(index);
        doubleNode<E> deletedItem;
        if(index<size/2) {
            deletedItem = head.next;
            for (int i = 0; i < index; i++) {
                deletedItem = deletedItem.next;
            }
        }
        else{
            deletedItem = tail.prev;
            for (int i = size-1; i > index; i--) {
                deletedItem = deletedItem.prev;
            }
        }
        deletedItem.prev.next=deletedItem.next;
        deletedItem.next.prev = deletedItem.prev;
        deletedItem.next=null;
        deletedItem.prev=null;
        deletedItem.element = null;
        size--;
    }


    public int size() {
        return size;
    }

    private void checkIndex(int index){
        if (index>=size || index <0)
            throw new RuntimeException("Invalid Index !");
    }
    public linkedlist<E> sublistCopy(int fromIndex, int toIndex) {
        doubleNode<E> current = head.next;
        linkedlist<E> sublist = new linkedlist<>();
        if (fromIndex <0 || toIndex > size || toIndex < fromIndex )
            throw new RuntimeException("Invalid Index !");
        for ( int i = 0; i < fromIndex ; i++) {
            current = current.next;
        }
        for ( int i = fromIndex; i < toIndex ; i++) {
            sublist.add(current.element);
            current = current.next;
        }
        return sublist;
    }


    public boolean contains(E o) {
        doubleNode<E> current = head.next;
        for ( int i = 0; i < size; i++) {
            if(current.element==o)
                return true;
            else
                current = current.next;
        }
        return false;
    }

    public void print (){
        doubleNode<E> current = head.next;
        System.out.println("STRAR");
        for (int i = 0; i < size ; i++) {
            System.out.print(current.element+" ");
            current = current.next;
        }
        System.out.println("END");
    }


    public linkedlist<E> ArrayToList (E[] arr)
    {
        linkedlist<E> myList = new linkedlist<>();
        for (E ints : arr) {
            myList.add(ints);
        }
        return myList;
    }
    //enhanced listtoarray
//    public E[][] listTo2DArray (){
//        E[][] myarray = new E [size/2][2];
//        doubleNode current = head.next;
//        for (int i = 0; i < size/2 ; i++) {
//            for (int j = 0; j < 2 ; j++) {
//                myarray[i][j] = current.element;
//                current = current.next;
//            }
//        }
//        return myarray;
//    }
    public Object[] toArray() {
        Object[] myArray = new Object[size];
        doubleNode<E> current = head.next;
        for (int i = 0; i < size ; i++) {
            myArray[i] = current.element;
            current = current.next;
        }
        return myArray;
    }

    public void ReverseList(){
        linkedlist<E> reversedList = new linkedlist<>();
        doubleNode<E> current = tail.prev;
        while (current!=head){
            reversedList.add(current.element);
            current = current.prev;
        }
        head = reversedList.head;
    }

    public static void main(String[] args) {
        linkedlist<Integer> trial = new linkedlist<>();
        for (int i = 0; i <10 ; i++) {
            trial.add(i);
        }
//        linkedlist<Integer> trial1 = trial.
//        linkedlist<Integer> trial = new linkedlist<>();
        LinkedList<Integer> o= new LinkedList<Integer>();
//		LinkedList<Integer> sun = o.subList(0,5);
//		 trial.ReverseList();
//		trial.print();


        linkedlist p = new linkedlist();
        p.add(3);
        p.add(2);
        p.add(1);
        p.add(0);

        linkedlist sublist = p.sublistCopy(1, 3);
        sublist.set(0,1);
        sublist.print();
        p.print();
        System.out.println(sublist.size);
    }

}
