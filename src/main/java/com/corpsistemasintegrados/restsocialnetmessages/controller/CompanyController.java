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
@RequestMapping("company")
@Api(tags = "company")
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
        if (obj.getCompanyName() != null){

            Company company = new Company();
            company.setCompanyName(obj.getCompanyName());
            company.setCreatedOn(LocalDateTime.now());
            repo.save(company);

            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.GET)
    public ResponseEntity getByName(@RequestParam("companyName") String companyName) {
            List<Company> company = repo.getByCompanyName(companyName);
            if (!company.isEmpty()) {
                return new ResponseEntity<>(company, HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Company> getById(@PathVariable("id") String id) {
        Company company = repo.getById(id);

        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Company> delete(@PathVariable("id") String id) {
        Company company = repo.getById(id);

        if (company != null) {
            repo.delete(company);
            return new ResponseEntity<>(company, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
