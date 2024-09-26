package com.VolodyaInc.url_shortener.service;

import com.VolodyaInc.url_shortener.algorithms.Base62;
import com.VolodyaInc.url_shortener.entity.Url;
import com.VolodyaInc.url_shortener.memoryStorage.MemoryStorage;
import com.VolodyaInc.url_shortener.repository.UrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Service
@Transactional
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private MemoryStorage memoryStorage;

    private boolean inMemoryStorage = false;

    public String shortenUrl(@RequestBody Url url) {

        String originalUrl = url.getOriginalUrl();
        url.setShortenedUrl(Base62.generateShortenedUrl(originalUrl));

        if (isInMemoryStorage()){
            memoryStorage.storeUrl(url);
        } else {
            urlRepository.save(url);
        }
        return url.getShortenedUrl();
    }

    public String getOriginalUrl(String shortenedUrl) {

        if (inMemoryStorage == false) {
            Url url = urlRepository.findByShortenedUrl(shortenedUrl);
            if (url != null) {
                return url.getOriginalUrl();
            } else {
                return null;
            }
        } else {
            Url url = memoryStorage.getUrl(shortenedUrl);
            return url.getOriginalUrl();
        }
    }

    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }

    public void setInMemoryStorage(boolean inMemoryStorage) {
        this.inMemoryStorage = inMemoryStorage;
    }

    public boolean isInMemoryStorage() {
        return inMemoryStorage;
    }
}

