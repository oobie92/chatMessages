package com.corpsistemasintegrados.restsocialnetmessages.repository;

import com.corpsistemasintegrados.restsocialnetmessages.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    ChatMessage getById(String id);

    List<ChatMessage> getBySender(String sender);

    List<ChatMessage> getByReceiver(String sender);

    List<ChatMessage> getByPlatform(String platform);
}
