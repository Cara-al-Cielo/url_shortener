package com.VolodyaInc.url_shortener.service;

import com.VolodyaInc.url_shortener.algorithms.Base62;
import com.VolodyaInc.url_shortener.entity.Url;
import com.VolodyaInc.url_shortener.repository.UrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String originalUrl) {
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortenedUrl("http://localhost:8080/" + generateShortenedUrl(originalUrl));
        urlRepository.save(url);
        return url.getShortenedUrl();
    }

    private String generateShortenedUrl(String originalUrl) {
        UUID uuid = UUID.randomUUID();
        return Base62.encodeUUID(uuid);
    }

    public String getOriginalUrl(String shortenedUrl) {
        Url url = urlRepository.findByShortenedUrl(shortenedUrl);
        if (url != null) {
            return url.getOriginalUrl();
        } else {
            return null;
        }
    }

    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }
}

