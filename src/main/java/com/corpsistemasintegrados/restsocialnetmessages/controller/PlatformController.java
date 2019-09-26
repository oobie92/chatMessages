package com.corpsistemasintegrados.restsocialnetmessages.controller;

import com.corpsistemasintegrados.restsocialnetmessages.model.Platform;
import com.corpsistemasintegrados.restsocialnetmessages.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("platform")
public class PlatformController {

    @Autowired
    private PlatformRepository repo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity findAll() {
        List<Platform> platforms;
        platforms = repo.findAll();

        if (!platforms.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(platforms, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByPlatformName", method = RequestMethod.GET)
    public ResponseEntity findByPlatformName(@RequestParam("platformName") String platformName) {
        Platform platform = repo.findByPlatformName(platformName);

        if (platform == null) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(platform, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Platform> save(@RequestBody() Platform obj) {
        if (obj.getPlatformName() != null && obj.getId() != null){
            obj.setCreatedOn(LocalDateTime.now());
            repo.save(obj);
            return new ResponseEntity<>(obj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Platform> delete(@PathVariable("id") String id) {
        Platform platform = repo.getById(id);

        if (platform != null) {
            repo.delete(platform);
            return new ResponseEntity<>(platform, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
