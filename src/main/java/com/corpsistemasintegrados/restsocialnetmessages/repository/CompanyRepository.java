package com.corpsistemasintegrados.restsocialnetmessages.repository;

import com.corpsistemasintegrados.restsocialnetmessages.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
}
