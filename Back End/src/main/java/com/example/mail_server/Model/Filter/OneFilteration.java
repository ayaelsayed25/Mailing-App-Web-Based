package com.example.mail_server.Model.Filter;

import com.example.mail_server.Model.Mail.indexMail;

import java.util.LinkedList;

public class OneFilteration implements FilterField {

    private FilterField senderFilter;
    private FilterField subjectFilter;



    public  OneFilteration (FilterField senderFilter, FilterField subjectFilter){
        this.senderFilter=senderFilter;
        this.subjectFilter=subjectFilter;
    }
    @Override
    public LinkedList<indexMail> filter(LinkedList<indexMail> mails) {
        LinkedList<indexMail> SubjectMails=subjectFilter.filter(mails);
        LinkedList<indexMail> SenderMails=senderFilter.filter(mails);

        for(indexMail mail :SubjectMails){
            if(!SenderMails.contains(mail)){
                SenderMails.add(mail);
            }
        }
        return SenderMails;
    }

    @Override
    public void setFilter(String subject) {
    }
}
