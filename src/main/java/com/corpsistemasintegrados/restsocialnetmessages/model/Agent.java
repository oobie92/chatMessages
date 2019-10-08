package com.corpsistemasintegrados.restsocialnetmessages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "agent")
@TypeAlias(value = "agent")
public class Agent extends DocumentId {

    private String name;
    @DBRef
    private Company company;
    private LocalDateTime createdOn;
}
