package com.example.mail_server.Model.Account;

import com.example.mail_server.Model.Contact;
import com.example.mail_server.Model.DataManagement.FileManager;
import com.example.mail_server.Model.Mail;
import com.example.mail_server.Model.Sort.ISortMail;
import com.example.mail_server.Model.Sort.SortFactory;
import com.example.mail_server.Model.Sort.SortText.IndicesSorting;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.google.gson.Gson;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Account {
    private String name;
    private String email;
    private String password;
    private LinkedList<Mail> currentFolderMails ;
    String currentFolderName;
    private LinkedList<Contact> contacts;
    private HashMap<String,LinkedList<Mail>> userFolders;
    private FileManager fileManager;




    public Account(){
        currentFolderMails=new LinkedList<Mail>();
        contacts=new LinkedList<Contact>();
        userFolders = new HashMap<String,LinkedList<Mail>>();
        fileManager = new FileManager();
    }

    public boolean CheckContactName(String name) throws IOException {
        String path = "./Accounts/" + email+ "/" + "contacts.json";
        JSONArray contacts = fileManager.listJsonObjects(path);

        for (Object contact : contacts) {
            JSONObject obj = (JSONObject) contact;
            if (((String) obj.get("name")).equalsIgnoreCase(name)) {
                return false;
            }
        }

        return true;
    }
    public LinkedList<Contact> loadContacts() throws IOException {
        String path = "./Accounts/" + email+ "/" + "contacts.json";
        JSONArray contacts = fileManager.listJsonObjects(path);
        LinkedList<Contact> contactList = new LinkedList<Contact>();
        for (Object o : contacts) {
            JSONObject obj = (JSONObject) o;
            Contact contact = new Contact();
            contact.setName((String) obj.get("name"));
            JSONArray Emails = new JSONArray();
            Emails = (JSONArray) obj.get("email");
            System.out.println(Emails.size());
            String[] Contact_Email = new String[Emails.size()];
            for (int j = 0; j < Emails.size(); j++) {

                Contact_Email[j] = (String) Emails.get(j);

            }
            contact.setEmail(Contact_Email);
            contactList.add(contact);
        }

       this.contacts=contactList;
        return contactList;
    }


    public LinkedList<Mail> loadFolder(String folderName) throws IOException {
        this.currentFolderName=folderName;
        String path = "./Accounts/" + email + "/" + folderName + "/index.json";
        System.out.println(path);
        JSONArray mails = fileManager.listJsonObjects(path);
        System.out.println(mails.toJSONString());
        System.out.println(mails.size()+"WWW");
        LinkedList<Mail> mailList = new LinkedList<>();
        for (Object o : mails) {
            JSONObject obj = (JSONObject) o;
            String[] receivers = new String[1];
            receivers[0] = obj.get("receiver").toString();
            Mail mail = new Mail((String) obj.get("subject"),(String) obj.get("body"),
                    (String) obj.get("sender"),receivers,(String) obj.get("date"), ((Long) obj.get("priority")).intValue());
            mail.setId((String) obj.get("id"));
//            mail.setSortedBody(new Gson().fromJson(obj.get("sortedBody"), IndicesSorting.indexedWord);
//            mail.setSortedSubject(obj.get("sortedSubject"));
            mailList.add(mail);
        }
        this.currentFolderMails=mailList;
        System.out.println("MAMA"+Arrays.toString(mailList.toArray()));
        return mailList;
    }

    public LinkedList<Mail> sortFolder(String sort){
        SortFactory sortFactory = new SortFactory();
        ISortMail sortMail = sortFactory.sortMails(sort);
        currentFolderMails = (LinkedList<Mail>) sortMail.Sort(currentFolderMails);
        return currentFolderMails;
    }

    public void addUserFolder(String folderName){}

    public LinkedList<Mail> getUserFolder(String folderName) {
        return userFolders.get(folderName);
    }

    public LinkedList<Mail> getCurrentFolderMails() {
        return currentFolderMails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LinkedList<Contact> getContacts() {
        return contacts;
    }

    public String getCurrentFolderName() {
        return currentFolderName;
    }
}
