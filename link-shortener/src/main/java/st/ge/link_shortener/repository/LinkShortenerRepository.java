package st.ge.link_shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import st.ge.link_shortener.entity.ShortenedLink;

@Repository
public interface LinkShortenerRepository extends JpaRepository<ShortenedLink, String>{

}
