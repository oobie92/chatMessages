package com.corpsistemasintegrados.restsocialnetmessages.controller;

import com.corpsistemasintegrados.restsocialnetmessages.model.Agent;
import com.corpsistemasintegrados.restsocialnetmessages.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("agent")
public class AgentController {

    @Autowired
    private AgentRepository repo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Agent>> findAll() {
        List<Agent> agents;
        agents = repo.findAll();

        if (!agents.isEmpty()) {
            return new ResponseEntity<>(agents, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.GET)
    public ResponseEntity<Agent> getByName(@RequestBody() Agent obj) {
        if (obj.getName() != null) {
            Agent agent = repo.findAgentByNameContains(obj.getName());
            if (agent != null) {
                return new ResponseEntity<>(agent, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Agent> save(@RequestBody() Agent obj) {
        if (obj.getName() != null){
            Agent newAgent = repo.save(obj);
            if (newAgent != null) {
                return new ResponseEntity<>(newAgent, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id) {
        Agent agent = repo.getById(id);
        if (agent != null){
            repo.delete(agent);
            return new ResponseEntity<>(agent, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
