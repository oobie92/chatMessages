package com.corpsistemasintegrados.restsocialnetmessages.controller;

import com.corpsistemasintegrados.restsocialnetmessages.model.ChatMessage;
import com.corpsistemasintegrados.restsocialnetmessages.model.Metrics;
import com.corpsistemasintegrados.restsocialnetmessages.repository.ChatMessageRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("metrics")
@Api(tags = "metrics")
public class MetricsController {

//    2. Tiempo medio de atencion por usuario, inicio a fin y un promedio

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @RequestMapping(value = "/totalChatsPerDayAndTime", method = RequestMethod.GET)
    public ResponseEntity findTotalChatsPerDayAndTime(@RequestParam("from") String from,
                                               @RequestParam("to") String to) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime formatDateTime1 = LocalDateTime.parse(from, formatter);
            LocalDateTime formatDateTime2 = LocalDateTime.parse(to, formatter);

            List<ChatMessage> messages = chatMessageRepository.getAllByCreatedOnBetween(formatDateTime1, formatDateTime2);

            if (messages.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);

            Metrics metrics = new Metrics((Integer.toString(messages.size())));

            return new ResponseEntity<>(metrics, HttpStatus.OK);
    }

    @RequestMapping(value = "/totalChatsPerDay", method = RequestMethod.GET)
    public ResponseEntity findTotalChatsPerDay(@RequestParam("from") String from,
                                               @RequestParam("to") String to) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formatDateTime1 = LocalDate.parse(from, formatter);
        LocalDate formatDateTime2 = LocalDate.parse(to, formatter);

        List<ChatMessage> messages = chatMessageRepository.getAllByCreatedOnBetween(formatDateTime1, formatDateTime2);

        if (messages.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);

        Metrics metrics = new Metrics((Integer.toString(messages.size())));

        return new ResponseEntity<>(metrics, HttpStatus.OK);
    }

    @RequestMapping(value = "/totalChatsPerGroup", method = RequestMethod.GET)
    public ResponseEntity findTotalChatsPerGroup(@RequestParam("groupName") String groupName) {

        List<ChatMessage> messages = chatMessageRepository.getAllByGroup(groupName);

        if(messages.isEmpty()) {
            Metrics metrics = new Metrics("0");
            return new ResponseEntity<>(metrics, HttpStatus.OK);
        }
        Metrics metrics = new Metrics((Integer.toString(messages.size())));

        return new ResponseEntity<>(metrics, HttpStatus.OK);
    }

}
