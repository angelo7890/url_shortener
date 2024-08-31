package com.anjox.url_shortener.repository;
import com.anjox.url_shortener.entities.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    Optional<Link> findBynomeLink(String nomeLink);
}
