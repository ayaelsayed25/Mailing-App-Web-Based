package com.example.mail_server.Model.Sort.SortContact;

import com.example.mail_server.Model.Contact;

public class SortWithContactName extends SortContact {

    @Override
    String getStringAttribute(Contact contact) {
        return contact.getName();
    }
}
