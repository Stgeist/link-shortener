package st.ge.link_shortener.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class LinkShortenerService {

    private final Map<String, String> urlStorage = new HashMap<>();
    
    private static final String chars =
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int shortCodeLength = 6;

    private static final SecureRandom random = new SecureRandom();
    
    private String generateShortCode() {

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < shortCodeLength; i++) {
            builder.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        return builder.toString();
    }

    public String shortenUrl(String baseUrl) {

        String generatedCode;

        if (!baseUrl.startsWith("http://") && !baseUrl.startsWith("https://")) {
            throw new IllegalArgumentException("Invalid URL format");
        }

        do {
            generatedCode = generateShortCode();
        } while (urlStorage.containsKey(generatedCode));

        urlStorage.put(generatedCode, baseUrl);

        return generatedCode;
    }

    public String getOriginalUrl(String shortCode) {

        String url = urlStorage.get(shortCode);

        return url;
    }
}
