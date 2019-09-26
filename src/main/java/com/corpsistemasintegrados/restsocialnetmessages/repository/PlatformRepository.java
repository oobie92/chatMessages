package com.corpsistemasintegrados.restsocialnetmessages.repository;

import com.corpsistemasintegrados.restsocialnetmessages.model.Platform;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends MongoRepository<Platform, String> {

    Platform findByPlatformName(String name);

    Platform getById(String id);
}
