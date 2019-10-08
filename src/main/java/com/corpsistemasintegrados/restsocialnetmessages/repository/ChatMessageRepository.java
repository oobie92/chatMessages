package com.corpsistemasintegrados.restsocialnetmessages.repository;

import com.corpsistemasintegrados.restsocialnetmessages.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    ChatMessage getById(String id);

    List<ChatMessage> getAllByCreatedOnBetween(LocalDateTime from, LocalDateTime to);

    List<ChatMessage> getAllByCreatedOnBetween(LocalDate from, LocalDate to);

    List<ChatMessage> getAllByGroup(String groupName);

    List<ChatMessage> getBySenderId(String sender);

    List<ChatMessage> getBySenderIdAndPlatform(String sender, String platform);

    List<ChatMessage> getByReceiverId(String receiver);

    List<ChatMessage> getByReceiverIdAndPlatform(String receiver, String platform);

    List<ChatMessage> getBySenderIdAndReceiverId(String sender, String receiver);

    List<ChatMessage> getByPlatform(String platform);
}
