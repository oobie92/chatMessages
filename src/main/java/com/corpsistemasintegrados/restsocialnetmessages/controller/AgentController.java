package com.corpsistemasintegrados.restsocialnetmessages.controller;

import com.corpsistemasintegrados.restsocialnetmessages.model.Agent;
import com.corpsistemasintegrados.restsocialnetmessages.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("agent")
public class AgentController {

    @Autowired
    private AgentRepository repo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Agent> getById() {
        return repo.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Agent save(@RequestBody() Agent obj) {
        if (obj.getName() != null) repo.save(obj);

        return obj;
    }

}
