package st.ge.link_shortener.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import st.ge.link_shortener.service.LinkShortenerService;

@RestController
@RequestMapping("/api")
public class LinkShortenerController {

    private final LinkShortenerService linkShortenerService;

    public LinkShortenerController(LinkShortenerService linkShortenerService) {
    this.linkShortenerService = linkShortenerService;
    }

    @PostMapping("/shorten")
    public String addBaseUrl(@RequestParam String baseUrl) {
        return linkShortenerService.shortenUrl(baseUrl);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToBaseUrl(@PathVariable String shortCode) {
        
        String baseUrl = linkShortenerService.getOriginalUrl(shortCode);

        if (baseUrl == null) {
            return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .build();
        }

        return ResponseEntity
        .status(HttpStatus.FOUND)
        .location(URI.create(baseUrl))
        .build();
    }
}
