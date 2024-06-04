package org.example.webapitableau.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;
@Entity
public class UserNotifcationEnitity {

    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    private Long id;
    private String notifytoken;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNotifytoken() {
        return notifytoken;
    }

    public void setNotifytoken(String notifytoken) {
        this.notifytoken = notifytoken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}

