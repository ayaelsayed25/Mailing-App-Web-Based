package com.example.mail_server.Model.Account;

import com.example.mail_server.Model.DataManagement.FileManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;

public class AccountProxy {

    public Account checkPassword(String email, String password) throws IOException {
        FileManager manager = new FileManager();
        JSONArray array = manager.listJsonObjects("./Accounts/Accounts.json");
        for (Object o : array) {
            JSONObject object = (JSONObject) o;
            if (object.get("email").equals(email) && object.get("password").equals(password)) {
                Account account = new Account();
                account.setEmail(email);
                account.setName((String) object.get("name"));
                account.setPassword(password);
                return account;
            }
        }
        return null;
    }
    public boolean checkEmail(String email){
        File folder = new File("./Accounts");
        File[] list = folder.listFiles();
        assert list != null;
        for(File file : list)
        {
            if(file.getName().equalsIgnoreCase(email))
            {
                return true;
            }
        }
        return false;
    }

}
