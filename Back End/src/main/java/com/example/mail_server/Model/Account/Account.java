package com.example.mail_server.Model.Account;

import com.example.mail_server.Model.Contact;
import com.example.mail_server.Model.DataManagement.Directory;
import com.example.mail_server.Model.DataManagement.FileManager;
import com.example.mail_server.Model.Search.*;
import com.example.mail_server.Model.Sort.SortContact.ISortContact;
import com.example.mail_server.Model.Sort.SortContact.SortContactFactory;
import com.example.mail_server.Model.Sort.SortMail.ISortMail;
import com.example.mail_server.Model.Mail.indexMail;
import com.example.mail_server.Model.Sort.SortMail.SortMailFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Account {
    private String name;
    private String email;
    private String password;
    private LinkedList<indexMail> currentFolderindexMails ;
    String currentFolderName;
    private LinkedList<Contact> contacts;
    private HashMap<String,LinkedList<indexMail>> userFolders;
    private FileManager fileManager;




    public Account(){
        currentFolderindexMails= new LinkedList<>();
        contacts= new LinkedList<>();
        userFolders = new HashMap<>();
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


    public LinkedList<indexMail> loadFolder(String folderName,int page) throws IOException {
        int start=page*11-10;int end =page*11;
        int counter=1;
        this.currentFolderName=folderName;
        String path = "./Accounts/" + email + "/" + folderName + "/index.json";
        JSONArray mails = fileManager.listJsonObjects(path);
        LinkedList<indexMail> mailList = new LinkedList<>();
        for (Object o : mails) {
            JSONObject obj = (JSONObject) o;
            String receiver = obj.get("receiver").toString();
            indexMail mail = new indexMail((String) obj.get("subject"),(String) obj.get("body"),
                    (String) obj.get("sender"),receiver,(String) obj.get("date"), ((Long) obj.get("priority")).intValue());
            mail.setId((String) obj.get("id"));
            mail.setDeleteDate((String) obj.get("deleteDate"));
            JSONArray sortedBody = (JSONArray) obj.get("sortedBody");
            indexedWord[] sortedBodywow = new indexedWord[sortedBody.size()];
            int i=0;
            for(Object s : sortedBody){
                JSONObject jsonObject = (JSONObject) s ;
                indexedWord castedObject = new indexedWord((String)jsonObject.get("word"),
                        ((Long) jsonObject.get("start")).intValue(),((Long) jsonObject.get("end")).intValue());
                sortedBodywow[i] = castedObject;
                i++;
            }

            JSONArray sortedSubject = (JSONArray) obj.get("sortedBody");
            indexedWord[] sortedSubjectwow = new indexedWord[sortedSubject.size()];
            i=0;
            for(Object s : sortedSubject){
                JSONObject jsonObject = (JSONObject) s ;
                indexedWord castedObject = new indexedWord((String)jsonObject.get("word"),
                        ((Long) jsonObject.get("start")).intValue(),((Long) jsonObject.get("end")).intValue());
                sortedSubjectwow[i] = castedObject;
                i++;
            }
            mail.setSortedBody(sortedBodywow);
            mail.setSortedBody(sortedSubjectwow);
            if(counter>=start&&counter<=end) {
                mailList.add(mail);
                if (counter == end)
                    break;
            }
            counter++;

        }
        this.currentFolderindexMails=mailList;
        return this.currentFolderindexMails;
    }

    public LinkedList<indexMail> sortFolder(String sort){
        SortMailFactory sortMailFactory = new SortMailFactory();
        ISortMail sortMail = sortMailFactory.sortMails(sort);
        if(currentFolderindexMails!=null && !currentFolderindexMails.isEmpty())
            currentFolderindexMails = (LinkedList<indexMail>) sortMail.Sort(currentFolderindexMails);
        return currentFolderindexMails;
    }

    public LinkedList<Contact> sortContacts(String sort){
        SortContactFactory sortContactFactory = new SortContactFactory();
        ISortContact sortContact = sortContactFactory.sortContacts(sort);
        if(contacts!=null && !contacts.isEmpty())
            contacts = (LinkedList<Contact>) sortContact.Sort(contacts);
        return contacts;
    }

    public LinkedList<mailSearchResults> searchFolder(String target){
        searchMailsFacade Search = new searchMailsFacade();
        return  Search.search(getCurrentFolderMails(),target);
    }
    public LinkedList<Contact> searchContacts(String target){
        searchContacts Search = new searchContacts();
        return  Search.search(contacts,target);
    }
    public LinkedList<indexMail> autoDelete(LinkedList<indexMail> indexMails) throws IOException {
        Directory dir = new Directory();
        FileManager file = new FileManager();
        for(int i=0; i<indexMails.size();)
        {
            long days = getTimeDifference(indexMails.get(i).getDeleteDate());
            if(days >= 30)
            {
                file.deleteFromIndex("./Accounts/"+getEmail()+"/"+getCurrentFolderName()+"/index.json",indexMails.get(i).getId());
                dir.DeleteFolder(new File("./Accounts/"+getEmail()+"/"+getCurrentFolderName() +"/" + indexMails.get(i).getId()));
                indexMails.remove(i);
            }
            else
                i++;
        }
        return indexMails;
    }

    public long getTimeDifference(String date)
    {
        SimpleDateFormat formatter= new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss");
        Date now = new Date(System.currentTimeMillis());
        long diffDays = 0;
        try {
            Date d1 = formatter.parse(date);
            Date d2 = formatter.parse(formatter.format(now));
            //in milliseconds
//            long diff = d2.getTime() - d1.getTime();
//            long diffSeconds = diff / 1000 % 60;
//            long diffMinutes = diff / (60 * 1000) % 60;
//            long diffHours = diff / (60 * 60 * 1000) % 24;
//            diffDays = diff / (24 * 60 * 60 * 1000);
//            System.out.print(diffDays + " days, ");
//            System.out.print(diffHours + " hours, ");
//            System.out.print(diffMinutes + " minutes, ");
//            System.out.print(diffSeconds + " seconds.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return diffDays;
    }
    public void addUserFolder(String folderName){}

    public LinkedList<indexMail> getUserFolder(String folderName) {
        return userFolders.get(folderName);
    }

    public LinkedList<indexMail> getCurrentFolderMails() {
        return currentFolderindexMails;
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
