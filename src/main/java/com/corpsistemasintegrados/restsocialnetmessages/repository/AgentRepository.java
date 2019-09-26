package com.corpsistemasintegrados.restsocialnetmessages.repository;

import com.corpsistemasintegrados.restsocialnetmessages.model.Agent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends MongoRepository<Agent, String> {

    Agent findAgentByNameContains(String name);
}
