package com.corpsistemasintegrados.restsocialnetmessages.controller;

import com.corpsistemasintegrados.restsocialnetmessages.model.Agent;
import com.corpsistemasintegrados.restsocialnetmessages.model.Company;
import com.corpsistemasintegrados.restsocialnetmessages.repository.AgentRepository;
import com.corpsistemasintegrados.restsocialnetmessages.repository.CompanyRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("agent")
@Api(tags = "agent")
public class AgentController {

    @Autowired
    private AgentRepository repo;

    @Autowired
    private CompanyRepository companyRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Agent>> findAll() {
        List<Agent> agents;
        agents = repo.findAll();

        if (!agents.isEmpty()) {
            return new ResponseEntity<>(agents, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.POST)
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
        if (obj.getName() != null && obj.getCompany().getId() != null){

            Company company = companyRepository.getById(obj.getCompany().getId());
            if (company == null) return  new ResponseEntity<>(HttpStatus.NO_CONTENT);

            Agent newAgent = new Agent();
            newAgent.setCompany(company);
            newAgent.setName(obj.getName());
            newAgent.setCreatedOn(LocalDateTime.now());
            repo.save(newAgent);
            return new ResponseEntity<>(newAgent, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("id") String id) {
        Agent agent = repo.getById(id);
        if (agent != null){
            return new ResponseEntity<>(agent, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
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
