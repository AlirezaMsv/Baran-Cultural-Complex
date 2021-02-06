package com.example.baran21;

public class Meeting {
    private String date;
    private String num;
    private String note;

    public Meeting(String date, String note) {
        this.setDate(date);
        this.setNote(note);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
