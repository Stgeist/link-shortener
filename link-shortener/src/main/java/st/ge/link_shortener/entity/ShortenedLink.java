package st.ge.link_shortener.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ShortenedLink {
    
    @Id
    private String shortCode;

    private String baseUrl;

    private int clickCount;  
    
    public ShortenedLink() {};

    public ShortenedLink(String shortCode, String baseUrl) {
        this.shortCode = shortCode;
        this.baseUrl = baseUrl;
        this.clickCount = 0;
    };
}
