package com.anjox.url_shortener.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name="links")
@Getter
@Setter
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column ( name = "url_original",nullable = false)
    private String linkOriginal;

    @Column(nullable = false)
    private String nomeLink;

    public Link() {
    }

    public Link(Long id, String linkOriginal, String nomeLink) {
        this.id = id;
        this.linkOriginal = linkOriginal;
        this.nomeLink = nomeLink;
    }
}
