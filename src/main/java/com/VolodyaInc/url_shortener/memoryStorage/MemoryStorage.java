package com.VolodyaInc.url_shortener.memoryStorage;
import com.VolodyaInc.url_shortener.entity.Url;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class MemoryStorage {

    private Map<String, Url> urlMap = new HashMap<>();

    public void storeUrl(Url url) {
        urlMap.put(url.getShortenedUrl(), url);
    }

    public Url getUrl(String shortenedUrl) {
        return urlMap.get(shortenedUrl);
    }
}