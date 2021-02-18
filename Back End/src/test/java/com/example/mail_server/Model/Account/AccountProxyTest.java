package com.example.mail_server.Model.Account;

import com.example.mail_server.Model.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.testng.asserts.Assertion;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class AccountProxyTest  {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testCheckPassword() throws IOException {
        AccountProxy accountProxy = new AccountProxy();
        User user = User.getInstance();
        user.signUp("john","john@gmail.com","john");
      Account accountTest = accountProxy.checkPassword("john@gmail.com","john");
        Assertions.assertEquals("john@gmail.com",accountTest.getEmail());
        Assertions.assertEquals("john",accountTest.getPassword());

    }

    @Test
    public void testCheckEmail() {
        AccountProxy accountProxy = new AccountProxy();
        Assertions.assertTrue(accountProxy.checkEmail("john@gmail.com"));
        Assertions.assertFalse(accountProxy.checkEmail("falseMail@gmail.com"));
    }
}
