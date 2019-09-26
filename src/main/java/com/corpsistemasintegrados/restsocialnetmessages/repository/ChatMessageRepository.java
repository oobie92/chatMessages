package com.corpsistemasintegrados.restsocialnetmessages.repository;

import com.corpsistemasintegrados.restsocialnetmessages.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}
