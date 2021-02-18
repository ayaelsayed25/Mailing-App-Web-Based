package com.example.mail_server.Model.Sort.SortContact;

import com.example.mail_server.Model.Contact;

public class SortWithContactEmail extends SortContact {
    @Override
    String getStringAttribute(Contact contact) {
        return contact.getEmail()[0];
    }
}
