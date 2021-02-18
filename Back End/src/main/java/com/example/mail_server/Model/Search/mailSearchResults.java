package com.example.mail_server.Model.Search;

public class mailSearchResults {
    Object source;
    Interval[] subjectOccurrences;
    Interval[] bodyOccurrences;
    Interval[] senderOccurrences;
    Interval[] priorityOccurrences;
    Interval[] dateOccurrences;

    public mailSearchResults(Object source, Interval[] subjectOccurrences, Interval[] bodyOccurrences, Interval[] senderOccurrences, Interval[] priorityOccurrences, Interval[] dateOccurrences) {
        this.source = source;
        this.subjectOccurrences = subjectOccurrences;
        this.bodyOccurrences = bodyOccurrences;
        this.senderOccurrences = senderOccurrences;
        this.priorityOccurrences = priorityOccurrences;
        this.dateOccurrences = dateOccurrences;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Interval[] getSubjectOccurrences() {
        return subjectOccurrences;
    }

    public void setSubjectOccurrences(Interval[] subjectOccurrences) {
        this.subjectOccurrences = subjectOccurrences;
    }

    public Interval[] getBodyOccurrences() {
        return bodyOccurrences;
    }

    public void setBodyOccurrences(Interval[] bodyOccurrences) {
        this.bodyOccurrences = bodyOccurrences;
    }

    public Interval[] getSenderOccurrences() {
        return senderOccurrences;
    }

    public void setSenderOccurrences(Interval[] senderOccurrences) {
        this.senderOccurrences = senderOccurrences;
    }

    public Interval[] getPriorityOccurrences() {
        return priorityOccurrences;
    }

    public void setPriorityOccurrences(Interval[] priorityOccurrences) {
        this.priorityOccurrences = priorityOccurrences;
    }

    public Interval[] getDateOccurrences() {
        return dateOccurrences;
    }

    public void setDateOccurrences(Interval[] dateOccurrences) {
        this.dateOccurrences = dateOccurrences;
    }
}
