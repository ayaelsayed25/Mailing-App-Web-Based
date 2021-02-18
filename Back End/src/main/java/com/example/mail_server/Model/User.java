package com.example.mail_server.Model;

import com.example.mail_server.Model.Account.Account;
import com.example.mail_server.Model.Account.AccountBuilder;
import com.example.mail_server.Model.Account.AccountProxy;
import com.example.mail_server.Model.DataManagement.Directory;
import com.example.mail_server.Model.DataManagement.FileManager;
import com.example.mail_server.Model.Filter.FilterField;
import com.example.mail_server.Model.Filter.Filteration;
import com.example.mail_server.Model.Filter.SenderField;
import com.example.mail_server.Model.Filter.SubjectField;
import com.example.mail_server.Model.Mail.Mail;
import com.example.mail_server.Model.Mail.indexMail;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class User {

    private Account currentUser;
    private static AccountProxy proxy;
    private static AccountBuilder builder;
    private static User firstInstance;

    private User() {}
    public static User getInstance()
    {
        if(firstInstance == null){
            firstInstance = new User();
            proxy = new AccountProxy();
            builder = new AccountBuilder();
        }
        return firstInstance;
    }
    public boolean signUp(String name, String email, String password) throws IOException {
        if(proxy.checkEmail(email))
            return false;
        currentUser = builder.build(name, email, password);
        return true;
    }

    public boolean signIn(String email, String password) throws IOException {
        currentUser = proxy.checkPassword(email, password);
        return currentUser != null;
    }

    public boolean createNewFolder(String folderName) throws IOException {
        Directory dir = new Directory();
        if(dir.createUserFolder(folderName, this.currentUser))
        {
            FileManager file = new FileManager();
            String path = "./Accounts/" + this.currentUser.getEmail() + "/" + folderName + "/index.json";
            file.listJsonObjects(path);
            return true;
        }
        return false;
    }

    public boolean addContact(Contact contact) throws IOException {
        if(!currentUser.CheckContactName(contact.getName())){
            return false;
        }
        for (String email: contact.getEmail()) {
            if(!proxy.checkEmail(email)){
                return false; }
        }
        System.out.println("aaaaaaaaaaaaaaa");
        FileManager json = new FileManager();
        String path="./Accounts/"+currentUser.getEmail()+"/contacts.json";
        json.addContact(contact,path);

        return true;
    }

    public LinkedList<indexMail> filter(String senderField, String subjectField){
        LinkedList<indexMail> mails = currentUser.getCurrentFolderMails();
        FilterField sender= new SenderField();
        sender.setFilter(senderField);
        FilterField subject= new SubjectField();
        subject.setFilter(subjectField);
        FilterField totalFilter= new Filteration(sender,subject);
        if(senderField==null){
            mails=subject.filter(mails); }
        else if(subjectField==null){
            mails=sender.filter(mails); }
        else{
            mails=totalFilter.filter(mails); }

        return mails;

    }

    public boolean draft(Mail mail) throws IOException {
        mail.setSender(currentUser.getEmail());
        this.saveMail(mail,currentUser.getEmail(),"draft");
        return true;
    }

    public boolean Compose(Mail mail) throws IOException {

        if(!checkReceivers(mail)){return false;}
        mail.setSender(currentUser.getEmail());
        this.saveMail(mail,currentUser.getEmail(),"sent");
        for (String receiver: mail.getReceivers()) {
          this.saveMail(mail,receiver,"inbox");
        }
        return true;
    }

    public boolean checkReceivers(Mail mail){
        for (String receiver: mail.getReceivers()) {
            if(!proxy.checkEmail(receiver)){
                return false;
            }
        }
        return true;
    }
    public void saveMail(Mail mail,String E_mail,String folder) throws IOException {
        Directory directory=new Directory();
        FileManager json = new FileManager();
        String myPath = "./Accounts/"+E_mail+"/"+folder+"/index.json";
        mail.setId(json.setNewID(myPath));
        directory.createFolder("./Accounts/"+E_mail+"/"+folder+"/"+mail.getId());
        String path="./Accounts/"+E_mail+"/"+folder+"/"+mail.getId()+"/"+mail.getId()+".json";
        json.saveJsonFile(mail, path);

        indexMail indexMail = new indexMail(mail.getSubject(),mail.getBody(),mail.getSender(),mail.getReceivers()[0],mail.getDate(),mail.getPriority());
        indexMail.setId(mail.getId());
        json.addMailToIndex(indexMail, myPath);
    }

    public boolean moveMail(String[] id,String folderName) throws IOException {
        Directory dir = new Directory();
        if(!dir.checkFolderExistence(folderName, this.currentUser))
            return false;
        FileManager json=new FileManager();
        LinkedList<indexMail> mails=currentUser.getCurrentFolderMails();
        for (String s : id) {
            for (indexMail mail : mails) {
                if (mail.getId().equalsIgnoreCase(s)) {
                    mails.remove(mail);
                    if(folderName.equalsIgnoreCase("trash"))
                    {
                        SimpleDateFormat formatter= new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss");
                        Date now = new Date(System.currentTimeMillis());
                        System.out.println("hellooooooooooooo");
                        mail.setDeleteDate(formatter.format(now));
                    }
                    json.moveMail(s, currentUser, mail, folderName);

                    break;
                }

            }
        }

        return true;
    }

    public String[] getUserFoldersList()
    {
        String path = "./Accounts/" + currentUser.getEmail();
        File dir = new File(path);
        String[] directories = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                boolean bool1 = new File(current, name).isDirectory();
                boolean bool2 = (name.equals("inbox") || name.equals("sent") || name.equals("draft") || name.equals("trash"));
                return bool1 && !bool2;
            }
        });
        return directories;
    }
    public boolean renameFolders(String folderName, String newFolderName)
    {
        Directory dir = new Directory();
        String path = "./Accounts/" + currentUser.getEmail() + "/" + folderName;
        String newPath = "./Accounts/" + currentUser.getEmail() + "/" + newFolderName;
        if(newFolderName.charAt(newFolderName.length() - 1) == ' ')
            return false;
        if(!dir.folderNameIsValid(newPath, newFolderName))
            return false;
        File sourceFile = new File(path);
        File destFile = new File(newPath);
        if(destFile.exists())
            return false;
        boolean bool= dir.renameMyFolder(sourceFile,destFile);
        if(!bool)
            return bool;
        dir.DeleteFolder(sourceFile);
        return bool;
    }
    public boolean deleteFolders (String folderName)
    {
        Directory dir = new Directory();
        String path = "./Accounts/" + this.currentUser.getEmail() + "/" + folderName;
        return dir.DeleteFolder(new File(path));
    }
    public Account getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Account currentUser) {
        this.currentUser = currentUser;
    }
}
