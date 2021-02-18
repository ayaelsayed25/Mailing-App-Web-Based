package com.example.mail_server.Model.Sort.SortMail.SortText;

import com.example.mail_server.Model.Mail.indexMail;

import java.io.File;

public class SortWithAttachment extends SortText {

    @Override
    String getStringAttribute(indexMail mail) {
        File f = new File(mail.getAttachments()[0]);
        return f.getName();
    }
}
