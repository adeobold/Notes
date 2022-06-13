package com.android1.notes;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Note implements Parcelable {
    private Date date;
    private String caption;
    private String note;

    public Note(String caption, String note) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2022);
        cal.set(Calendar.MONTH, Calendar.MAY);
        cal.set(Calendar.DAY_OF_MONTH, 22);
        Date noteDate = cal.getTime();

        this.date = noteDate;
        this.caption = caption;
        this.note = note;
    }

    public Note(Date date, String caption, String note) {
        this.date = date;
        this.caption = caption;
        this.note = note;
    }

    protected Note(Parcel in) {
        caption = in.readString();
        note = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(caption);
        parcel.writeString(note);
    }
}
