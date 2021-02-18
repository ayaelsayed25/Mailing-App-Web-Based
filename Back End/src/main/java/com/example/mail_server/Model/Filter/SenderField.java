package com.example.mail_server.Model.Filter;

import com.example.mail_server.Model.Mail.indexMail;

import java.util.LinkedList;

public class SenderField implements FilterField {
    private String sender;
    @Override
    public LinkedList<indexMail> filter(LinkedList<indexMail> mails) {
        LinkedList<indexMail> SenderMails = new LinkedList<>();

        System.out.println(sender);

        for(indexMail mail : mails){
            if(mail.getSender().contains(sender)){
                SenderMails.add(mail);
            }
        }

        return SenderMails;
    }

    public void setFilter(String sender) {
        this.sender = sender;
    }
}
