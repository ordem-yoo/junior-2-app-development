package kr.ac.mjc.chatting;

import java.util.Date;

public class Message {

    private String text;
    private Date sendDate;
    private String nickname;

    public Message(){
        sendDate = new Date();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
