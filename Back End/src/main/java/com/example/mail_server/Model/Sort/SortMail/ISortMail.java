package com.example.mail_server.Model.Sort.SortMail;

import com.example.mail_server.Model.Mail.indexMail;

import java.util.LinkedList;
import java.util.List;

public interface ISortMail {
    List<indexMail> Sort(LinkedList<indexMail> mails);
}
