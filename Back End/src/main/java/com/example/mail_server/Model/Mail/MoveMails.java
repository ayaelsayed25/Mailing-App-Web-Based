package com.example.mail_server.Model.Mail;

public class MoveMails {
    String folderName;
    String[] id;

    public MoveMails(String folderName, String[] id) {
        this.folderName = folderName;
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String[] getId() {
        return id;
    }

    public void setId(String[] id) {
        this.id = id;
    }
}
