package com.example.mail_server.Model.DataStructures;

public interface ListIterator<E> {

    public boolean hasNext();
    public E next();
    public E previous();
    public boolean hasPrevious();
    public void set(E e);
}

