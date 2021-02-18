package com.example.mail_server.Model.DataManagement;

import com.example.mail_server.Model.Account.Account;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Directory{


    public void createFolder(String path){
        File folder = new File(path);
        System.out.println(folder.mkdir());
    }
    public void CopyFolder(File source ,File target,String id){
        try {
            if(source.isDirectory()) {
                copy(source,target,id);
            }else {
                if(target.getName().equalsIgnoreCase(source.getParentFile().getName()+".json")){
                    target=new File(target.getParent()+"/"+id+".json");
                }
                Files.copy(source.toPath(), target.toPath());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void copy(File source,File target,String id) throws IOException{
        try {
            if(source.exists()) {
                if(!target.exists()) {
                    target.mkdir();
                }
                for(String child : source.list()) {
                    CopyFolder(new File(source,child),new File(target,child),id);
                }
            }else {
                throw new RuntimeException("File to be copied doesn't exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean DeleteFolder(File file) { // to delete folders and files but not txt
        if (file.exists()) {
            for (File subFile : file.listFiles()) {
                if (subFile.isDirectory()) {
                    DeleteFolder(subFile);
                } else {
                    subFile.delete();
                }
            }
            file.delete();
            return true;
        } else {
            return false;
        }
    }

    public boolean createUserFolder(String folderName, Account account)
    {
        String path = "./Accounts/" + account.getEmail() + "/" + folderName;
        if(folderName.charAt(folderName.length() - 1) == ' ')
            return false;
        if(!folderNameIsValid(path, folderName))
            return false;
        File folder = new File(path);
        if(folder.exists())
            return false;
        return folder.mkdir();
    }
    public boolean renameMyFolder(File source, File target)
    {
        try {
            if (source.isDirectory()) {
                rename(source, target);
            } else {
                Files.copy(source.toPath(), target.toPath());
            }
        }catch (Exception er) {
            er.printStackTrace();
            return false;
        }
        return true;
    }
    public void rename(File source,File target) throws Exception{
        if(source.exists()) {
            if(!target.exists()) {
                target.mkdir();
            }
            for(String child : source.list()) {
                renameMyFolder(new File(source,child),new File(target,child));
            }
        }else {
            throw new RuntimeException("File to be copied doesn't exist");
        }
    }
    public boolean folderNameIsValid(String file, String name)
    {
        File f = new File(file);
        try {
            f.getCanonicalPath();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean checkFolderExistence(String name, Account acc)
    {
        String path = "./Accounts/" + acc.getEmail() + "/" + name;
        File folder = new File(path);
        if(folder.exists())
            return true;
        return false;
    }

}
