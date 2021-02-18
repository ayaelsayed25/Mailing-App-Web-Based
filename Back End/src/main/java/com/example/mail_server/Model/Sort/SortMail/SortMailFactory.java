package com.example.mail_server.Model.Sort.SortMail;

import com.example.mail_server.Model.Sort.SortMail.SortText.SortWithAttachment;
import com.example.mail_server.Model.Sort.SortMail.SortText.SortWithBody;
import com.example.mail_server.Model.Sort.SortMail.SortText.SortWithSender;
import com.example.mail_server.Model.Sort.SortMail.SortText.SortWithSubject;
import com.example.mail_server.Model.Sort.SortWithDate;

public class SortMailFactory {
    public ISortMail sortMails(String sortAccordingTo){
        if(sortAccordingTo.equalsIgnoreCase("subject"))
            return new SortWithSubject();
        else if (sortAccordingTo.equalsIgnoreCase("body"))
            return new SortWithBody();
        else if (sortAccordingTo.equalsIgnoreCase("sender"))
            return new SortWithSender();
        else if (sortAccordingTo.equalsIgnoreCase("date"))
            return new SortWithDate();
        else if(sortAccordingTo.equalsIgnoreCase("priority"))
            return new SortWithPriority();
        else if(sortAccordingTo.equalsIgnoreCase("attachments"))
            return new SortWithAttachment();
        return null;
    }

}
