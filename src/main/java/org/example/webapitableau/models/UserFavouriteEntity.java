package org.example.webapitableau.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;
@Entity
public class UserFavouriteEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    private Long id;
    private String uid;
    private String csvfavourites;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCsvfavourites() {
        return csvfavourites;
    }

    public void setCsvfavourites(String csvfavourites) {
        this.csvfavourites = csvfavourites;
    }
}
