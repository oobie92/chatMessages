package com.corpsistemasintegrados.restsocialnetmessages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "platform")
@TypeAlias(value = "platform")
public class Platform {

    @Id
    private String id;
    private String platformName;
    private LocalDateTime createdOn;

}
