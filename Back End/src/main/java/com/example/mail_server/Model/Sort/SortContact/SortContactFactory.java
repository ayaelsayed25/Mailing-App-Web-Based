package com.example.mail_server.Model.Sort.SortContact;

public class SortContactFactory {
    public ISortContact sortContacts(String sortAccordingTo){
        if(sortAccordingTo.equalsIgnoreCase("email"))
            return new SortWithContactEmail();
        else if (sortAccordingTo.equalsIgnoreCase("name"))
            return new SortWithContactName();
        return null;
    }
}
