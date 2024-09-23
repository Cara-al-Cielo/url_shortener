package com.VolodyaInc.url_shortener.controller;

import com.VolodyaInc.url_shortener.entity.Url;
import com.VolodyaInc.url_shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import com.VolodyaInc.url_shortener.service.UrlService;

import java.net.URI;

@RestController

@RequestMapping("/")

public class UrlController {
    @Autowired
    private UrlService urlService;
    @Autowired
    private UrlRepository urlRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createShortUrl(@RequestBody Url url) {
        String shortenedUrl = urlService.shortenUrl(url);
        return ResponseEntity.ok(shortenedUrl);
    }

    @GetMapping("/urls")
    public ResponseEntity<String> getOriginalUrl(@RequestParam("shortenedUrl") String shortenedUrl) {
        Url url = urlRepository.findByShortenedUrl(shortenedUrl);
        if (url != null) {
            return ResponseEntity.ok(url.getOriginalUrl());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
