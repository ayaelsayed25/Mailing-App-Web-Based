package com.example.mail_server.Controller;

import com.example.mail_server.Model.*;
import com.example.mail_server.Model.Account.Account;
import com.example.mail_server.Model.DataManagement.FileManager;
import com.example.mail_server.Model.Mail.Mail;
import com.example.mail_server.Model.Mail.MoveMails;
import com.example.mail_server.Model.Mail.indexMail;
import com.example.mail_server.Model.Search.mailSearchResults;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;

@Configuration
@Deprecated
class MyConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedHeaders("*");
            }
        };
    }

}
@RestController
@CrossOrigin
public class controller {
    private User user;
    LinkedList<LinkedList<String>> file;

    public controller(){
        user = User.getInstance();
        file = new LinkedList<>();
    }
    @CrossOrigin
    @PostMapping("/compose")
    public boolean compose(@RequestBody Mail mail) throws IOException {
        LinkedList<String> temp = file.peek();
        if(temp != null)
             mail.setAttachments(file.pop());
        else mail.setAttachments(new LinkedList<>());
        return user.Compose(mail);
    }

    @CrossOrigin
    @PostMapping("/attachment")
    public boolean attach(@RequestParam("file") MultipartFile[] file) throws IOException {
        LinkedList<String> attachments = new LinkedList<>();
        for(MultipartFile file0 : file) {
            byte[] bytes = file0.getBytes();
            String path ="./Accounts/Attachments/" + file0.getOriginalFilename();
            attachments.add(path);
            Files.write(Paths.get(path),bytes);
            System.out.println("YARAb");
            System.out.println(Arrays.toString(bytes));
        }
        this.file.add(attachments);
        return true;
    }

    @CrossOrigin
    @GetMapping("/sendAttachment")
    public LinkedList<byte[]> sendAttach(@RequestParam(value = "path") String[] paths) throws IOException {
        FileManager fileManager = new FileManager();
//        System.out.println(paths.length);
        LinkedList<byte[]> attachments = new LinkedList<>();
        for (String path : paths){
            path = URLDecoder.decode(path, StandardCharsets.UTF_8);
            File file = new File(path);
//            System.out.println(path);

            byte[] bArray = fileManager.readFileToByteArray(file);
            attachments.add(bArray);
        }
//        File file = new File(paths[0]);

//        String path = URLDecoder.decode(paths[0], StandardCharsets.UTF_8);

//        byte[] bArray = Files.readAllBytes(Path.of("./Accounts/Attachments/2011sm.jpg"));
//        System.out.println(bArray);
//        return ResponseEntity.ok().contentLength(bArray.length).header(HttpHeaders.CONTENT_DISPOSITION).body(bArray);
        return attachments;
    }


    @CrossOrigin
    @PostMapping("/draft")
    public boolean draft(@RequestBody Mail mail) throws IOException {
        return user.draft(mail);
    }

    @CrossOrigin
    @RequestMapping("/createAccount")
    @ResponseBody
    public boolean createAccount(@RequestParam (value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "password") String password) throws IOException {
        return user.signUp(name, email, password);
    }
    @CrossOrigin
    @RequestMapping("/signIn")
    @ResponseBody
    public boolean signIn( @RequestParam(value = "email") String email, @RequestParam(value = "password") String password) throws IOException {
        return user.signIn(email, password);
    }

    @CrossOrigin
    @RequestMapping("/getMails")
    @ResponseBody
    public LinkedList<indexMail> getListMails(@RequestParam(value = "folderName") String folderName, @RequestParam(value = "pageNumber") int pageNumber) throws IOException {
        Account acc = user.getCurrentUser();
        return acc.loadFolder(folderName,pageNumber);
    }

    @CrossOrigin
    @RequestMapping("/sortMails")
    @ResponseBody
    public LinkedList<indexMail> sortMails(@RequestParam(value = "sort") String sort) {
        //new ??
        Account acc = user.getCurrentUser();
        return acc.sortFolder(sort);
    }

    @CrossOrigin
    @RequestMapping("/searchMails")
    @ResponseBody
    public LinkedList<mailSearchResults> searchMails(@RequestParam(value = "target") String target) {
        Account acc = user.getCurrentUser();
        return acc.searchFolder(target);
    }
    @CrossOrigin
    @RequestMapping("/searchContacts")
    @ResponseBody
    public LinkedList<Contact> searchContacts(@RequestParam(value = "target") String target) {
        Account acc = user.getCurrentUser();
        return acc.searchContacts(target);
    }
    @CrossOrigin
    @RequestMapping("/filter")
    @ResponseBody
    public LinkedList<indexMail> getFilteredMails(@RequestParam(value = "sender") String senderField,@RequestParam(value = "subject") String subjectField) {
        return user.filter(senderField,subjectField);
    }


    @CrossOrigin
    @PostMapping("/addContact")
    public boolean  addContact(@RequestBody Contact contact) throws IOException {
        return user.addContact(contact);
    }

    @CrossOrigin
    @RequestMapping("/sortContacts")
    @ResponseBody
    public LinkedList<Contact> sortContacts(@RequestParam(value = "sort") String sort) {
        //new ??
        Account acc = user.getCurrentUser();
        return acc.sortContacts(sort);
    }

    @CrossOrigin
    @GetMapping("/removeContact")
    public boolean  removeContact(@RequestParam (value = "id") String id ) throws IOException {
        String path = "./Accounts/" + user.getCurrentUser().getEmail() + "/contacts.json";
        FileManager fileManager = new FileManager();
        fileManager.removeContact(path,id);
        return true;
    }

    @CrossOrigin
    @RequestMapping("/getContacts")
    @ResponseBody
    public LinkedList<Contact> getContacts() throws IOException {
        Account acc = user.getCurrentUser();
        LinkedList<Contact> contacts = acc.loadContacts();
        return contacts;
    }
    @CrossOrigin
    @PostMapping ("/move")
    @ResponseBody
    public boolean moveMails(@RequestBody MoveMails moveMail) throws IOException {
         return user.moveMail(moveMail.getId(), moveMail.getFolderName());
    }

    @CrossOrigin
    @RequestMapping("/addFolder")
    @ResponseBody
    public boolean addFolder(@RequestParam(value = "folderName") String folderName) throws IOException {
      return User.getInstance().createNewFolder(folderName);
    }

    @CrossOrigin
    @RequestMapping("/getUserFolders")
    @ResponseBody
    public String[] getUserFolders(){
        return User.getInstance().getUserFoldersList();
    }
    @CrossOrigin
    @RequestMapping("/openMail")
    @ResponseBody
    public Mail openMail(@RequestParam(value = "id") String id, @RequestParam(value = "currentFolder") String currentFolder) throws IOException, ParseException, ParseException {
        String path = "./Accounts/" + user.getCurrentUser().getEmail() + "/" + currentFolder + "/" + id +"/"+id +".json";
        FileManager fileManager = new FileManager();
        Mail mail = fileManager.getMailContent(path);
//        for(String attach : mail.getAttachments()){
//            System.out.println(attach);
//            System.out.println("tmaaam");
//        }
        return mail;
    }
    @CrossOrigin
    @RequestMapping("/renameFolder")
    @ResponseBody
    public boolean renameFolder(@RequestParam(value = "folderName") String folderName, @RequestParam(value = "newFolderName") String newFolderName){
        System.out.println(folderName + "   " + newFolderName);
        return User.getInstance().renameFolders(folderName, newFolderName);
    }

    @CrossOrigin
    @RequestMapping("/deleteFolder")
    @ResponseBody
    public boolean deleteFolder(@RequestParam(value = "folderName") String folderName){
        System.out.println(folderName);
        return User.getInstance().deleteFolders(folderName);
    }

}
