package com.hotelsystem.hotelsystem.web.controller;


import com.hotelsystem.hotelsystem.web.data_models.Guestbook;
import com.hotelsystem.hotelsystem.web.repositories.GuestbookRepository;
import com.hotelsystem.hotelsystem.web.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GuestbookController {
    @Autowired
    GuestbookRepository guestbookRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/guestbook")
    public ResponseEntity<List<Guestbook>> getAllOpinions() {
        return ResponseEntity.ok().body(guestbookRepository.findAll());
    }

    @PostMapping("/guestbook")
    public ResponseEntity<Guestbook> addOpinion(@RequestBody Map<String, String> body) {
        String nickname = body.get("nickname");
        String opinion = body.get("opinion");

        Guestbook newOpinion;
        if (nickname.isEmpty() || opinion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            newOpinion = new Guestbook(opinion, nickname);
            return ResponseEntity.ok().body(guestbookRepository.save(newOpinion));

        }
    }

    @PutMapping("/guestbook/{id}")
    public ResponseEntity<Guestbook> updateOpinion(@PathVariable("id") Long opinion_id, @RequestBody Map<String, String> body) {
        String nickname = body.get("nickname");
        String opinion = body.get("opinion");

        Guestbook updatedOpinion;
        if (nickname.isEmpty() || opinion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            if (guestbookRepository.findById(opinion_id).isPresent()) {
                updatedOpinion = guestbookRepository.findById(opinion_id).get();
                updatedOpinion.setOpinion(opinion);
                updatedOpinion.setNickname(nickname);
                return ResponseEntity.ok().body(guestbookRepository.save(updatedOpinion));
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }

    }
    @DeleteMapping("/guestbook/{id}")
    public ResponseEntity<Void> deleteOpinion(@PathVariable("id") Long opinion_id){
        Guestbook getOpinion;
        if(guestbookRepository.findById(opinion_id).isPresent()){
            getOpinion = guestbookRepository.findById(opinion_id).get();
            guestbookRepository.delete(getOpinion);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
