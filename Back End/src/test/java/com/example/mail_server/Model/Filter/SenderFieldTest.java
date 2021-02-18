package com.example.mail_server.Model.Filter;

import com.example.mail_server.Model.Mail.indexMail;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.logging.Filter;

import static org.junit.jupiter.api.Assertions.*;

class SenderFieldTest {
    LinkedList<indexMail> mails = new LinkedList<>();
    @Before
    void setup() {
        for (int i = 0; i < 5; i++) {
            indexMail temp = new indexMail("subject"+ i ,"body" + i,"sender"+i,"receiver"+i,"date"+i,i );
            mails.add(temp);
        }
    }

    @Test
    void filter() {
        FilterField filterField = new SenderField();
        filterField.setFilter("sender 1");
        filterField.filter(mails);
    }
}