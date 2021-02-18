package com.example.mail_server.Model.Mail;

import com.example.mail_server.Model.Search.IndicesSorting;

public class indexMail {

    private String subject;
    private String body;
    private String sender;
    private String receiver;
    private String date;
    private int priority;
    private String[] attachments;
    private String id = "0" ;
    private Object[] sortedBody;
    private Object[] sortedSubject;
    private IndicesSorting indicesSorting = new IndicesSorting();

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    private String deleteDate ;

    public indexMail(String subject, String body, String sender, String receiver, String date, int priority) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.priority = priority;
        this.sortedBody = indicesSorting.sortMailContent(body);
        this.sortedSubject = indicesSorting.sortMailContent(subject);
        //this.attachments = attachments;

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.sortedSubject = indicesSorting.sortMailContent(subject);
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.sortedBody = indicesSorting.sortMailContent(body);
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String[] getAttachments() {
        return attachments;
    }

    public void setAttachments(String[] attachments) {
        this.attachments = attachments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object[] getSortedBody() {
        return sortedBody;
    }

    public Object[] getSortedSubject() {
        return sortedSubject;
    }

    public void setSortedBody(Object[] sortedBody) {
        this.sortedBody = sortedBody;
    }

    public void setSortedSubject(Object[] sortedSubject) {
        this.sortedSubject = sortedSubject;
    }


}
