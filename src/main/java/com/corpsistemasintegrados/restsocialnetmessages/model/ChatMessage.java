package com.corpsistemasintegrados.restsocialnetmessages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "chat_message")
@TypeAlias(value = "chat_message")
public class ChatMessage extends DocumentId {

    private String senderId;
    private String receiverId;
    private String platform;
    private String message;
    private String group;
    private LocalDateTime createdOn;
}
