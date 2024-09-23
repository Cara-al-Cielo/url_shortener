package com.VolodyaInc.url_shortener.repository;

import com.VolodyaInc.url_shortener.entity.Url;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    Url findByShortenedUrl(String shortenedUrl);
}
