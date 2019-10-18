/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpsistemasintegrados.restsocialnetmessages.repository;

import com.corpsistemasintegrados.restsocialnetmessages.model.Agent;
import com.corpsistemasintegrados.restsocialnetmessages.model.Client;
import com.corpsistemasintegrados.restsocialnetmessages.model.ClientSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Sisint
 */
public interface ClientSessionRepository extends MongoRepository<ClientSession, String> {
    
    ClientSession getById(String id);
    
    ClientSession getBySessionCreated(LocalDateTime sessionCreated);
    
    List<ClientSession> getAllBySessionCreatedBetween(LocalDateTime from, LocalDateTime to);
    
    List<ClientSession> getAllBySessionCreatedBetween(LocalDate from, LocalDate to);
    
    List<ClientSession> getByClient(Client client);
    
    List<ClientSession> getByAgent(Agent agent);        
    
    List<ClientSession> getAllBySessionCreated(LocalDateTime from, LocalDateTime to);
}
