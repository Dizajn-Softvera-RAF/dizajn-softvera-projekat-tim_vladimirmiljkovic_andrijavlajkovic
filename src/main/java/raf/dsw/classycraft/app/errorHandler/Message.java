package raf.dsw.classycraft.app.errorHandler;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Message {
    private String content;
    private MessageType type;
    private LocalDateTime timestamp;

    public Message(String content, MessageType type) {
        this.content = content;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

}
