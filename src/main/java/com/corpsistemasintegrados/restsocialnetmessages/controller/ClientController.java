package com.corpsistemasintegrados.restsocialnetmessages.controller;

import com.corpsistemasintegrados.restsocialnetmessages.model.Client;
import com.corpsistemasintegrados.restsocialnetmessages.model.Company;
import com.corpsistemasintegrados.restsocialnetmessages.payload.HandleErrorPayload;
import com.corpsistemasintegrados.restsocialnetmessages.repository.ClientRepository;
import com.corpsistemasintegrados.restsocialnetmessages.repository.CompanyRepository;
import io.swagger.annotations.Api;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("client")
@Api(tags = "client")
public class ClientController {

    @Autowired
    private ClientRepository repo;

    @Autowired
    private CompanyRepository companyRepository;

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
            client = repo.findByLastname(obj.getLastname());

            if (client == null){
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody() Client obj) {
        System.out.println(obj);
        if (obj.getName() != null &&
            obj.getId() != null &&
            obj.getLastname() != null &&
            obj.getEmail() != null &&
            obj.getPlatformName() != null &&
            obj.getCompany().getCompanyName() != null || obj.getCompany().getCompanyName().equals("")){            
            List<Company> companies = companyRepository.getByCompanyName(obj.getCompany().getCompanyName().toLowerCase());
            if(!companies.isEmpty()){
                
                Company company = companies.get(0);
                
                System.out.println("company");
                System.out.println(company);                
                Client client = new Client();
                client.setId(obj.getId());
                client.setName(obj.getName());
                client.setPlatformName(obj.getPlatformName().toUpperCase());
                client.setCompany(company);
                client.setLastname(obj.getLastname());
                client.setEmail(obj.getEmail());
                client.setCreatedOn(LocalDateTime.now());
                repo.save(client);

                URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/RestSocialNetMessages/client/{name}")
                    .buildAndExpand(client.toString()).toUri();

                return ResponseEntity.created(location).body(client);
                
            }
            
            return  new ResponseEntity<HandleErrorPayload>(new HandleErrorPayload("Company doesn't exist"), HttpStatus.CONFLICT);
            
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> getById(@PathVariable("id") String id) {

        Client client = repo.getById(id);

        if (client == null)  return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Client> delete(@PathVariable("id") String id) {
        Client client = repo.getById(id);

        if (client != null) {
            repo.delete(client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
