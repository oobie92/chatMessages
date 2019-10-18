package com.corpsistemasintegrados.restsocialnetmessages.controller;

import com.corpsistemasintegrados.restsocialnetmessages.model.ChatMessage;
import com.corpsistemasintegrados.restsocialnetmessages.model.ClientSession;
import com.corpsistemasintegrados.restsocialnetmessages.model.Metrics;
import com.corpsistemasintegrados.restsocialnetmessages.payload.HandleErrorPayload;
import com.corpsistemasintegrados.restsocialnetmessages.payload.MetricPayload;
import com.corpsistemasintegrados.restsocialnetmessages.repository.ChatMessageRepository;
import com.corpsistemasintegrados.restsocialnetmessages.repository.ClientSessionRepository;
import io.swagger.annotations.Api;
import java.text.DecimalFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("metrics")
@Api(tags = "metrics")
public class MetricsController {

//    2. Tiempo medio de atencion por usuario, inicio a fin y un promedio

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    
    @Autowired
    private ClientSessionRepository clientSessionRepository;
    
    @RequestMapping(value = "/serveAveragePerDayAndTime", method = RequestMethod.GET)
    public ResponseEntity serveAveragePerDayAndTime(@RequestParam("from") String from,
                                                    @RequestParam("to") String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatDateTime1 = LocalDateTime.parse(from, formatter);
        LocalDateTime formatDateTime2 = LocalDateTime.parse(to, formatter);                       
        
        List<ClientSession> list = clientSessionRepository.getAllBySessionCreatedBetween(formatDateTime1, formatDateTime2);
        List<ClientSession> newList = new ArrayList<>();
        if(!list.isEmpty()) {
                
            int add = 0;
            String average = "";
            double secs = 0;
            double result = 0;
            double takenResult = 0;
            double leavedResult = 0;
            DecimalFormat df2 = new DecimalFormat("#.##");
            
            for(ClientSession c : list){
                if(c.getSessionTaken() != null && c.getSessionLeaved() != null){                    
                    secs = (double) c.getSessionTaken().getSecond()/60;
                    takenResult = (double) (c.getSessionTaken().getHour()*60)+(c.getSessionTaken().getMinute())+secs;                    
                    secs = (double) c.getSessionLeaved().getSecond()/60;
                    leavedResult = (double) (c.getSessionLeaved().getHour()*60)+c.getSessionLeaved().getMinute()+secs;                    
                    result += leavedResult-takenResult;                    
                    add++;                    
                }                                
                                
            }
                                    
            average = df2.format(result/add);            
            
            return new ResponseEntity<>(new MetricPayload(average + " minutes"), HttpStatus.OK);
            
        }
        
        return new ResponseEntity<>(new HandleErrorPayload("There are not any client session to the specified date"),HttpStatus.NO_CONTENT);
    
    }
    
    @RequestMapping(value = "/serveAveragePerDay", method = RequestMethod.GET)
    public ResponseEntity serveAveragePerDay(@RequestParam("from") String from,
                                               @RequestParam("to") String to) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formatDateTime1 = LocalDate.parse(from, formatter);
        LocalDate formatDateTime2 = LocalDate.parse(to, formatter);                       
        
        List<ClientSession> list = clientSessionRepository.getAllBySessionCreatedBetween(formatDateTime1, formatDateTime2);
        List<ClientSession> newList = new ArrayList<>();
        if(!list.isEmpty()) {
                
            int add = 0;
            String average = "";
            double secs = 0;
            double result = 0;
            double takenResult = 0;
            double leavedResult = 0;
            DecimalFormat df2 = new DecimalFormat("#.##");
            
            for(ClientSession c : list){
                if(c.getSessionTaken() != null && c.getSessionLeaved() != null){                    
                    secs = (double) c.getSessionTaken().getSecond()/60;
                    takenResult = (double) (c.getSessionTaken().getHour()*60)+(c.getSessionTaken().getMinute())+secs;                    
                    secs = (double) c.getSessionLeaved().getSecond()/60;
                    leavedResult = (double) (c.getSessionLeaved().getHour()*60)+c.getSessionLeaved().getMinute()+secs;                    
                    result += leavedResult-takenResult;                    
                    add++;                    
                }                                
                                
            }
                                    
            average = df2.format(result/add);            
            
            return new ResponseEntity<>(new MetricPayload(average + " minutes"), HttpStatus.OK);
            
        }
        
        return new ResponseEntity<>(new HandleErrorPayload("There are not any client session to the specified date"), HttpStatus.NO_CONTENT);
    
    }
//    
//    @RequestMapping(value = "/serveAveragePerAgent", method = RequestMethod.GET)
//    public ResponseEntity serveAveragePerAgent(@RequestParam("from") String from,
//                                               @RequestParam("to") String to) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime formatDateTime1 = LocalDateTime.parse(from, formatter);
//        LocalDateTime formatDateTime2 = LocalDateTime.parse(to, formatter);
//        return;
//    
//    }

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
