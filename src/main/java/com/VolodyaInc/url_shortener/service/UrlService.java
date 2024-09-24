package com.VolodyaInc.url_shortener.service;

import com.VolodyaInc.url_shortener.algorithms.Base62;
import com.VolodyaInc.url_shortener.entity.Url;
import com.VolodyaInc.url_shortener.repository.UrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(@RequestBody Url url) {

        String originalUrl = url.getOriginalUrl();
        url.setShortenedUrl(Base62.generateShortenedUrl(originalUrl));
        urlRepository.save(url);
        return url.getShortenedUrl();
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

