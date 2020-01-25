package com.hotelsystem.hotelsystem.web.data_models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Guestbook {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long opinion_id;
    private String opinion;
    private String nickname;

    public Guestbook(String opinion, String nickname){
        this.opinion = opinion;
        this.nickname = nickname;
    }
    public Guestbook(){}

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getOpinion_id() {
        return opinion_id;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }


}
