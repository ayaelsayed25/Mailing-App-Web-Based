package com.example.mail_server.Model.Search;

import com.example.mail_server.Model.Contact;

import java.util.LinkedList;

public class LinearSearch {
    public boolean search(String source, String target) {
        int index = source.indexOf(target);
        return index != -1;
    }
}
