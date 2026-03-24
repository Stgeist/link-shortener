package st.ge.link_shortener.service;
import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import st.ge.link_shortener.entity.ShortenedLink;
import st.ge.link_shortener.repository.LinkShortenerRepository;

@Service
public class LinkShortenerService {

    @Autowired
    private LinkShortenerRepository linkShortenerRepository;
    
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

    private void incrementClickCount(ShortenedLink link) {
        link.setClickCount(link.getClickCount() + 1);
        linkShortenerRepository.save(link);
    }

    public String shortenUrl(String baseUrl) {

        if (!baseUrl.startsWith("http://") && !baseUrl.startsWith("https://")) {
            throw new IllegalArgumentException("Invalid URL format");
        } 

        String generatedShortCode;

        do {
            generatedShortCode = generateShortCode();
        } while (linkShortenerRepository.existsById(generatedShortCode)); 

        ShortenedLink shortenedLink = new ShortenedLink(generatedShortCode, baseUrl);
        linkShortenerRepository.save(shortenedLink);

        return shortenedLink.getShortCode();
    }

    public String getOriginalUrl(String shortCode) {

        ShortenedLink link = linkShortenerRepository.findById(shortCode)
            .orElseThrow(() -> new RuntimeException("Short URL not found"));

        incrementClickCount(link);

        return link.getBaseUrl();
    }
}
