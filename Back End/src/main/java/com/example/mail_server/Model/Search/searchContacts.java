package com.example.mail_server.Model.Search;

import com.example.mail_server.Model.Contact;

import java.util.Arrays;
import java.util.LinkedList;

public class searchContacts {
    public LinkedList<Contact>search(LinkedList<Contact> contacts, String x){
        LinkedList<Contact> contactSearchResults = new LinkedList<>();
        LinearSearch linearSearch = new LinearSearch();
        for(Contact contact : contacts){
            if(linearSearch.search(contact.getName(),x)){
                contactSearchResults.add(contact);
            }
            else {
                for (String email:contact.getEmail()) {
                    if(linearSearch.search(email,x))
                        contactSearchResults.add(contact);
            }
        }
        }
        System.out.println("ALOOOOOOOOOOOOOOO");
        System.out.println(Arrays.toString(contactSearchResults.toArray()));
        return contactSearchResults;
    }
}
