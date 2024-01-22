package com.bekzod.cenference.controllers;

import com.bekzod.cenference.models.Session;
import com.bekzod.cenference.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> getAllSessions(){
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session getSession(@PathVariable Long id){
        return sessionRepository.getOne(id);
    }

    @PostMapping
    public Session addSession(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public void updateSession(@PathVariable Long id, @RequestBody Session session){
        Session existingObject = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingObject, "session_id");
        sessionRepository.saveAndFlush(existingObject);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity<?> deleteSession(@PathVariable Long id){
        try{
            sessionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (EmptyResultDataAccessException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
