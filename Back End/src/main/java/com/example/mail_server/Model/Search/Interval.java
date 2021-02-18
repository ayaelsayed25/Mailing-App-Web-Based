package com.example.mail_server.Model.Search;

import java.util.LinkedList;
import java.util.ListIterator;

public class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
    public static Interval[] toArray(LinkedList<Interval> intervals){
        ListIterator<Interval> listIterator = intervals.listIterator();
        Interval[] myArray = new Interval[intervals.size()];
        int i =0;
        while (listIterator.hasNext()){
            myArray[i] = listIterator.next();
        }
        return myArray;
    }
}
