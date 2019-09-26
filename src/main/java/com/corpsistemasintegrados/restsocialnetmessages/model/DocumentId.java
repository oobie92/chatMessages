package com.corpsistemasintegrados.restsocialnetmessages.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
class DocumentId {

    @Id
    private String id;
}
