package com.corpsistemasintegrados.restsocialnetmessages.controller;

import com.corpsistemasintegrados.restsocialnetmessages.model.ChatMessage;
import com.corpsistemasintegrados.restsocialnetmessages.repository.ChatMessageRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("chatMessages")
@Api(tags = "chatMessages")
public class ChatMessageController {

    @Autowired
    private ChatMessageRepository repo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity findAll() {
        List<ChatMessage> messages = repo.findAll();

        if (messages.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("id") String id) {
        ChatMessage message = repo.getById(id);

        if (message == null) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody() ChatMessage obj) {
        if (obj.getSenderId() != null &&
            obj.getReceiverId() != null &&
            obj.getMessage() != null &&
            obj.getPlatform() != null) {
            obj.setCreatedOn(LocalDateTime.now());
            repo.save(obj);


            return new ResponseEntity<>(obj, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getBySenderId", method = RequestMethod.POST)
    public ResponseEntity getBySenderId(@RequestBody() ChatMessage obj) {
        List<ChatMessage> messages;

        if (obj.getSenderId() != null && obj.getPlatform() != null) {
            messages = repo.getBySenderIdAndPlatform(obj.getSenderId(), obj.getPlatform());

        } else if (obj.getSenderId() != null) {
            messages = repo.getBySenderId(obj.getSenderId());


        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (messages.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @RequestMapping(value = "/getByReceiverId", method = RequestMethod.POST)
    public ResponseEntity getByReceiverId(@RequestBody() ChatMessage obj) {
        List<ChatMessage> messages;

        if (obj.getReceiverId() != null && obj.getPlatform() != null) {
            messages = repo.getByReceiverIdAndPlatform(obj.getReceiverId(), obj.getPlatform());
        } else if (obj.getReceiverId() != null) {
            messages = repo.getByReceiverId(obj.getReceiverId());


        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (messages.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @RequestMapping(value = "/getBySenderIdAndReceiverId", method = RequestMethod.POST)
    public ResponseEntity getBySenderIdAndReceiverId(@RequestBody() ChatMessage obj) {
        List<ChatMessage> messages;

        if (obj.getSenderId() != null && obj.getReceiverId() != null) {
            messages = repo.getBySenderIdAndReceiverId(obj.getSenderId(), obj.getReceiverId());

            if (messages.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);

        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @RequestMapping(value = "/getByPlatform", method = RequestMethod.POST)
    public ResponseEntity getByPlatform(@RequestBody() ChatMessage obj) {
        List<ChatMessage> messages;

        if (obj.getPlatform() != null) {
            messages = repo.getByPlatform(obj.getPlatform());

            if (messages.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);

        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ChatMessage> delete(@PathVariable("id") String id) {
        ChatMessage chatMessage = repo.getById(id);

        if (chatMessage != null) {
            repo.delete(chatMessage);
            return new ResponseEntity<>(chatMessage, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
