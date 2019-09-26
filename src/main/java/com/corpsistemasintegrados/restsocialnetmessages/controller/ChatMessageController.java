package com.corpsistemasintegrados.restsocialnetmessages.controller;

import com.corpsistemasintegrados.restsocialnetmessages.model.ChatMessage;
import com.corpsistemasintegrados.restsocialnetmessages.model.Client;
import com.corpsistemasintegrados.restsocialnetmessages.repository.ChatMessageRepository;
import com.corpsistemasintegrados.restsocialnetmessages.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("chatMessages")
public class ChatMessageController {

    @Autowired
    private ChatMessageRepository repo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity findAll() {
        List<ChatMessage> messages = repo.findAll();

        if (messages.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getById() {
        List<ChatMessage> messages = repo.findAll();

        if (messages.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
