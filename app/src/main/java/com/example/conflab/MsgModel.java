package com.example.conflab;

public class MsgModel {
    String msg,senderId;
    long timeStamp;

    public String getMsg() {
        return msg;
    }

    public MsgModel(String msg, String senderId, long timeStamp) {
        this.msg = msg;
        this.senderId = senderId;
        this.timeStamp = timeStamp;
    }
    public MsgModel() {

    }

    public String getSenderId() {
        return senderId;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
