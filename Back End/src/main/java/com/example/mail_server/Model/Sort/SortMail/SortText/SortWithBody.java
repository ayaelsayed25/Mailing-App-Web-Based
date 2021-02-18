package com.example.mail_server.Model.Sort.SortMail.SortText;

import com.example.mail_server.Model.Mail.indexMail;

public class SortWithBody extends SortText {

    @Override
    String getStringAttribute(indexMail mail) {
        return mail.getBody();
    }
}
