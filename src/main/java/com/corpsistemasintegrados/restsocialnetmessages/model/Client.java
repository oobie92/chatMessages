package com.corpsistemasintegrados.restsocialnetmessages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "client")
@TypeAlias(value = "client")
public class Client extends DocumentId {

    private String name;
    private String lastName;
    private String email;
    private LocalDateTime createdOn;
    @DBRef  private Platform platform;
}
