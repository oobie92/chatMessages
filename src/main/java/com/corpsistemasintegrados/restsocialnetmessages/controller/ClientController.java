package com.corpsistemasintegrados.restsocialnetmessages.controller;

import com.corpsistemasintegrados.restsocialnetmessages.model.Client;
import com.corpsistemasintegrados.restsocialnetmessages.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientRepository repo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity findAll() {
        List<Client> clients;
        clients = repo.findAll();

        if (clients.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.GET)
    public ResponseEntity findByName(@RequestBody Client obj) {
        Client client = new Client();
        if (obj.getName() != null) {
            client = repo.findByName(obj.getName());

            if (client == null){
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByLastName", method = RequestMethod.GET)
    public ResponseEntity findByLastName(@RequestBody Client obj) {
        Client client = new Client();
        if (obj.getName() != null) {
            client = repo.findByLastName(obj.getLastName());

            if (client == null){
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Client> save(@RequestBody() Client obj) {
        if (obj.getName() != null || obj.getPlatform() != null){
            obj.setCreatedOn(LocalDateTime.now());
            repo.save(obj);
        }

        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
