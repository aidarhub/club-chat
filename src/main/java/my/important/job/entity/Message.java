package my.important.job.entity;

import my.important.job.dto.MessageFindDto;

import java.time.LocalDate;

public class Message {
    private int messageId;
    private String text;
    private int senderId;
    private int receivedId;
    private LocalDate createTime;

    public Message() { }

    public MessageFindDto toDto() {
        return new MessageFindDto(messageId, text, senderId, receivedId, createTime);
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceivedId() {
        return receivedId;
    }

    public void setReceivedId(int receivedId) {
        this.receivedId = receivedId;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }


}
