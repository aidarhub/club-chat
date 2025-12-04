package my.important.job.model;

import my.important.job.entity.Message;
import my.important.job.entity.User;

import java.util.List;

public class Chat {
    List<Message> messageList;
    User userFrom;
    User userTo;

    public Chat() {
    }

    public Chat(List<Message> messageList, User userFrom, User userTo) {
        this.messageList = messageList;
        this.userFrom = userFrom;
        this.userTo = userTo;
    }
}
