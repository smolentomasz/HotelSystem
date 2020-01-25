package com.hotelsystem.hotelsystem.web.data_models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Opinion {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long opinion_id;
    private String opinion;

    @ManyToOne
    private User user;

    public Opinion(String opinion, User user){
        this.opinion = opinion;
        this.user = user;
    }
    public Opinion(){}
}
