package com.mp.bechefbackend.bechefbackend.Models;

public class InfoMessage {
    private String message;
    private int messageCode;
    public InfoMessage(String message, int messageCode) {
        this.message = message;
        this.messageCode = messageCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getMessageCode() {
        return messageCode;
    }
    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }


}
