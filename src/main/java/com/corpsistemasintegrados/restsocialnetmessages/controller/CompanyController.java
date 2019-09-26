package com.corpsistemasintegrados.restsocialnetmessages.controller;

import com.corpsistemasintegrados.restsocialnetmessages.model.Company;
import com.corpsistemasintegrados.restsocialnetmessages.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyRepository repo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity findAll() {
        List<Company> companies;
        companies = repo.findAll();

        if (companies.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Company> save(@RequestBody() Company obj) {
        if (obj.getName() != null || obj.getAgent() != null){
            repo.save(obj);
        }

        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
