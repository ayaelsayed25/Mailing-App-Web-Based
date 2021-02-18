package com.example.mail_server.Model.Sort.SortContact;

import com.example.mail_server.Model.Contact;
import com.example.mail_server.Model.Contact;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public abstract class SortContact implements ISortContact {
    @Override
    public List<Contact> Sort(LinkedList<Contact> mails){
        return divide(mails, 0, mails.size()-1);
    }

    LinkedList<Contact> merge(LinkedList<Contact> list, int l, int m, int r) {
        /* Create temp lists */
        List<Contact> L = new ArrayList<>(list.subList(l,m+1));
        List<Contact> R = new ArrayList<>(list.subList(m+1,r+1));
        /*Copy data to temp lists*/
        ListIterator<Contact> leftIterator = L.listIterator();
        ListIterator<Contact> rightIterator = R.listIterator();
        ListIterator<Contact> listIterator = list.listIterator(l);

        /* Merge the temp lists */
        while (leftIterator.hasNext() && rightIterator.hasNext()) {
            Contact left = leftIterator.next();
            Contact right = rightIterator.next();
            listIterator.next();
            if(getStringAttribute(left).compareToIgnoreCase(getStringAttribute(right))<0){
                listIterator.set(left);
                rightIterator.previous();
            } else {
                listIterator.set(right);
                leftIterator.previous();
            }
        }

        /* Copy remaining elements of L[] if any */
        while (leftIterator.hasNext()) {
            listIterator.next();
            listIterator.set(leftIterator.next());
        }

        /* Copy remaining elements of R[] if any */
        while (rightIterator.hasNext()) {
            listIterator.next();
            listIterator.set(rightIterator.next());
        }
        return list;

    }

    // Main function that sorts list[l..r] using
    // merge()
    List<Contact> divide(LinkedList<Contact> list, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            divide(list, l, m);
            divide(list, m + 1, r);

            // Merge the sorted halves
            return merge(list, l, m, r);
        }
        //walla null ?
        return new LinkedList<>();
    }
    abstract String getStringAttribute(Contact contact);
}
