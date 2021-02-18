package com.example.mail_server.Model.Filter;

import com.example.mail_server.Model.Mail.indexMail;

import java.util.LinkedList;


public class SubjectField implements FilterField {
    private  String subject;

    @Override
    public LinkedList<indexMail> filter(LinkedList<indexMail> mails) {
        LinkedList<indexMail> SubjectMails =new LinkedList<>();

        System.out.println(subject);

        for(indexMail mail : mails){
            if(mail.getSubject().contains(subject)){
                SubjectMails.add(mail);
            }
        }
        return SubjectMails;
    }


    public void setFilter(String subject){
        this.subject=subject;
    }
}
