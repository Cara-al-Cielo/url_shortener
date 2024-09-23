package com.VolodyaInc.url_shortener.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalUrl;

    @Column(unique = true)
    private String shortenedUrl;



    public Long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    public void setId(Long id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public void setOriginalUrl(String originalUrl) {
        if (originalUrl != null) {
            this.originalUrl = originalUrl;
        }
    }

    public void setShortenedUrl(String shortenedUrl) {
        if (shortenedUrl != null){
            this.shortenedUrl = shortenedUrl;
        }
    }
}
