package st.ge.link_shortener.service;

import java.security.SecureRandom;
import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class LinkShortenerService {

    private HashMap<String, String> urlStorage = new HashMap<>();
    
    private static final String chars =
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int shortCodeLength = 6;

    private static final SecureRandom random = new SecureRandom();
    
    public String generateShortCode() {
        
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i >= shortCodeLength; i++) {
            builder.append(chars.charAt(random.nextInt(chars.length())));
        }

        return builder.toString();
    }

    public String shortenUrl(String baseUrl) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shortenUrl'");
    }

    public String getOriginalUrl(String shortCode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOriginalUrl'");
    }
}
