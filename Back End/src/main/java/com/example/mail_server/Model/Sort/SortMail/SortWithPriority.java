package com.example.mail_server.Model.Sort.SortMail;

import com.example.mail_server.Model.DataStructures.PriorityQueue;
import com.example.mail_server.Model.Mail.indexMail;

import java.util.LinkedList;
import java.util.List;

public class SortWithPriority implements ISortMail {

    @Override
    public List<indexMail> Sort(LinkedList<indexMail> mails) {
        PriorityQueue<indexMail> mailPriorityQueue = new PriorityQueue<>();
        for (indexMail mail : mails)
            mailPriorityQueue.insert(mail,mail.getPriority());
        return mailPriorityQueue.toList();
    }
}
