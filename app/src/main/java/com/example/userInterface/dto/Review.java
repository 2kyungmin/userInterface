package com.example.userInterface.dto;

import java.util.Date;

public class Review {
    private String uId;
    private String name;
    private String challengeName;
    private String emoji;
    private String text;
    private int clickNum;
    private Date date;

    public Review() {
    }

    public Review(String uId, String name, String challengeName, String emoji, String text, int clickNum) {
        this.uId = uId;
        this.name = name;
        this.challengeName = challengeName;
        this.emoji = emoji;
        this.text = text;
        this.clickNum = clickNum;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Review{" +
                "uId='" + uId + '\'' +
                ", name='" + name + '\'' +
                ", challengeName='" + challengeName + '\'' +
                ", emoji='" + emoji + '\'' +
                ", text='" + text + '\'' +
                ", clickNum=" + clickNum +
                ", date=" + date +
                '}';
    }
    /*
    Getter, Setter
     */

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getClickNum() {
        return clickNum;
    }

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }

    public void plusClickNum(){
        clickNum++;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }
}