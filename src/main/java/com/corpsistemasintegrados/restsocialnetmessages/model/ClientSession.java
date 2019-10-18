/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpsistemasintegrados.restsocialnetmessages.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Sisint
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "client_session")
@TypeAlias(value = "client_session")
public class ClientSession extends DocumentId {

    @DBRef private Client client;
    private LocalDateTime sessionCreated;    
    @DBRef private Agent agent;
    private LocalDateTime sessionTaken;
    private LocalDateTime sessionLeaved;
    
}
