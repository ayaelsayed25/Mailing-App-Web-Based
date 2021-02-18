package com.example.mail_server.Model.Mail;

import java.net.URLDecoder;
import java.util.Base64;

public class Priority {
    public static String mapPriority(int priority){
        if(priority==1){
            return "Very Important";
        }
        else if(priority== 2){
            return "Important";
        }
        else if(priority==3){
            return "Normal";
        }
        else if(priority == 4){
           return "Not Important";
        }
        return "";
    }
}
