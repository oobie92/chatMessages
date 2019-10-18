/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpsistemasintegrados.restsocialnetmessages.controller;

import com.corpsistemasintegrados.restsocialnetmessages.model.Agent;
import com.corpsistemasintegrados.restsocialnetmessages.model.Client;
import com.corpsistemasintegrados.restsocialnetmessages.model.ClientSession;
import com.corpsistemasintegrados.restsocialnetmessages.repository.AgentRepository;
import com.corpsistemasintegrados.restsocialnetmessages.repository.ClientRepository;
import com.corpsistemasintegrados.restsocialnetmessages.repository.ClientSessionRepository;
import io.swagger.annotations.Api;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sisint
 */

@RestController
@RequestMapping("clientSession")
@Api(tags = "clientSession")
public class ClientSessionController {
    
    @Autowired
    private ClientSessionRepository repository;
    
    @Autowired
    private AgentRepository agentRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity findAll() {
        List<ClientSession> clientSession = repository.findAll();

        if (clientSession.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(clientSession, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody ClientSession obj) {
//        System.out.println("obj");
//        System.out.println(obj);
                
        if(obj.getId() != null){
            ClientSession cs = repository.getById(obj.getId());
            if(cs != null) {                
                if(cs.getAgent() == null){                   
                    if(obj.getAgent().getId() != null){
                        Agent agent = agentRepository.getById(obj.getAgent().getId());
                        if(agent != null) {
                            cs.setAgent(agent);
                            cs.setSessionTaken(LocalDateTime.now());
                            repository.save(cs);
                            return new ResponseEntity<>(cs, HttpStatus.OK);
                        }
                        
                        return new ResponseEntity<>(obj, HttpStatus.NO_CONTENT);
                    }                                        
                    
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }
                
                cs.setSessionLeaved(LocalDateTime.now());
                repository.save(cs);
                return new ResponseEntity<>(cs, HttpStatus.OK);
                
            } 
            
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        if (obj.getClient().getId() != null && obj.getClient().getCompany() != null){        
            Client c = clientRepository.getById(obj.getClient().getId());
//            System.out.println("c");
//            System.out.println(c);
            if(c != null) {
                obj.setClient(c);
                obj.setSessionCreated(LocalDateTime.now());
                repository.save(obj);
                return new ResponseEntity<>(obj, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        }
        
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id) {
        ClientSession cs = repository.getById(id);
        if(cs != null){
            repository.delete(cs);
            return new ResponseEntity<>(cs, HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
