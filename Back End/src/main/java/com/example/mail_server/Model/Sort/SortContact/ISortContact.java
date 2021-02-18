package com.example.mail_server.Model.Sort.SortContact;

import com.example.mail_server.Model.Contact;

import java.util.LinkedList;
import java.util.List;

public interface ISortContact {
    List<Contact> Sort(LinkedList<Contact> mails);
}
