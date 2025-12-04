package my.important.job.dto;

import my.important.job.entity.Message;

import java.time.LocalDate;

public record MessageFindDto(int id,
                             String text,
                             int senderId,
                             int receivedId,
                             LocalDate createTime) {
    public Message toEntity() {
        var message =  new Message();
        message.setSenderId(senderId);
        message.setReceivedId(receivedId);
        message.setText(text);
        message.setCreateTime(createTime);

        return message;
    }
}
