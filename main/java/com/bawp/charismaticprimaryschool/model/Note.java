package com.bawp.charismaticprimaryschool.model;

import java.util.Date;

public class Note {
    private int noteId;
    private String note_name;
    private String description;
    private Date createdDate;
    private String userName;

    public Note() {
    }

    public Note(String note_name, String description, Date createdDate, String userName) {
        this.note_name = note_name;
        this.description = description;
        this.createdDate = createdDate;
        this.userName = userName;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNote_name() {
        return note_name;
    }

    public void setNote_name(String note_name) {
        this.note_name = note_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
