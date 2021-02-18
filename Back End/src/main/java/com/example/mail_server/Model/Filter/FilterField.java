package com.example.mail_server.Model.Filter;

import java.util.LinkedList;
import com.example.mail_server.Model.Mail.indexMail;

public interface FilterField {
    public LinkedList<indexMail> filter(LinkedList<indexMail> mails);
    public void setFilter(String subject);
}
