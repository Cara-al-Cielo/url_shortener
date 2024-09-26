package com.VolodyaInc.url_shortener;

import com.VolodyaInc.url_shortener.entity.Url;
import com.VolodyaInc.url_shortener.memoryStorage.MemoryStorage;
import com.VolodyaInc.url_shortener.repository.UrlRepository;
import com.VolodyaInc.url_shortener.service.UrlService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EntityScan("com.VolodyaInc.url_shortener.entity")
@EnableJpaRepositories("com.VolodyaInc.url_shortener.repository")
public class UrlShortenerApplication {

	private final UrlService urlService;
	@Autowired
	public UrlShortenerApplication(UrlService urlService) {
		this.urlService = urlService;
	}
	@Autowired
	private MemoryStorage memoryStorage;

	@Value("${storage.type}")
	String storageType;

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}


	@PostConstruct
	public void init() {
		if (storageType.equals("memory")) {
			urlService.setInMemoryStorage(true);
		}
	}
}
