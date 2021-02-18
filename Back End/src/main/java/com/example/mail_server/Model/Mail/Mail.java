package com.example.mail_server.Model.Mail;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;

public class Mail {

    private String subject;
    private String body;
    private String sender;
    private String[] receivers;
    private String date;
    private int priority;
    private LinkedList<String> attachments;
    private String id = "0" ;

    public Mail(){}

    public Mail(String subject, String body, String sender, String[] receivers, String date, int priority,LinkedList<String> attachments) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.receivers = receivers;
        this.date = date;
        this.priority = priority;
        this.attachments = attachments;

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String[] getReceivers() {
        return receivers;
    }

    public void setReceivers(String[] receivers) {
        this.receivers = receivers;
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

    public LinkedList<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(LinkedList<String> attachments) throws IOException {
        this.attachments = attachments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
