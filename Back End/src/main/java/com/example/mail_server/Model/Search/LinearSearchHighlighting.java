package com.example.mail_server.Model.Search;

import java.util.LinkedList;

public class LinearSearchHighlighting {
    public Interval[] search(String source, String target){
        LinkedList<Interval> occurrences = new LinkedList<>();
        int index = source.indexOf(target);
        if(index!=-1)
            occurrences.add(new Interval(index,index+target.length()));
        return Interval.toArray(occurrences);
    }
}
