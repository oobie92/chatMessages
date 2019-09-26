package com.corpsistemasintegrados.restsocialnetmessages.repository;

import com.corpsistemasintegrados.restsocialnetmessages.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    ChatMessage getById(String id);

    List<ChatMessage> getBySenderId(String sender);

    List<ChatMessage> getBySenderIdAndPlatform(String sender, String platform);

    List<ChatMessage> getByReceiverId(String receiver);

    List<ChatMessage> getByReceiverIdAndPlatform(String receiver, String platform);

    List<ChatMessage> getBySenderIdAndReceiverId(String sender, String receiver);

    List<ChatMessage> getByPlatform(String platform);
}
