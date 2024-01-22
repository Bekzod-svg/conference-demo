package com.bekzod.cenference.controllers;

import com.bekzod.cenference.models.Speaker;
import com.bekzod.cenference.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/speakers")
public class SpeakersController {
    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> getSpeakersList(){
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker getSpeaker(@PathVariable Long id){
        return speakerRepository.getOne(id);
    }

    @PostMapping
    public Speaker addSpeaker(@RequestBody Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ResponseEntity<?> updateSpeaker(@Validated @RequestBody Speaker speaker, @PathVariable Long id){
        try{
            Speaker existingSpeaker = speakerRepository.getById(id);
            BeanUtils.copyProperties(speaker, existingSpeaker, "session_id");
            speakerRepository.saveAndFlush(existingSpeaker);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteSpeaker(@PathVariable Long id){
        speakerRepository.deleteById(id);
    }

}
