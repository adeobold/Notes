package com.android1.notes;

import java.util.Date;

public class Note {
    private Date date;
    private String caption;
    private String note;

    public Note(Date date, String caption, String note) {
        this.date = date;
        this.caption = caption;
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public String getCaption() {
        return caption;
    }

    public String getNote() {
        return note;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
