package com.anjox.url_shortener.service;

import com.anjox.url_shortener.entities.Link;
import com.anjox.url_shortener.entities.LinkDTO;
import com.anjox.url_shortener.repository.LinkRepository;
import org.springframework.stereotype.Service;
import java.text.Normalizer;
import java.util.UUID;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    private String generate(){
        return UUID.randomUUID().toString().substring(0,5);
    }

    private String format(String nomelink){
        String normalized = Normalizer.normalize(nomelink, Normalizer.Form.NFD);
        return normalized
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }

    public LinkDTO save(LinkDTO linkDTO){
        Link link = new Link();

        link.setLinkOriginal(linkDTO.linkorignal());

        var nmlk= format(linkDTO.nomelink());
        var existsslug = linkRepository.findBynomeLink(nmlk);

        if (existsslug.isPresent()){
            throw new IllegalArgumentException("Link already exists");
        }
        if(nmlk.isEmpty() || nmlk.isEmpty()){
            nmlk = this.generate();
        }

        link.setNomeLink(nmlk);

        this.linkRepository.save(link);

        return new LinkDTO(
                link.getLinkOriginal(),
                link.getNomeLink()
        );
    }
    public String redirect(String nomelink) {

        var link = getnomeLink(nomelink);

        return link.getLinkOriginal();
    }


    private Link getnomeLink(String nomelink){
        var lk = this.linkRepository.findBynomeLink(nomelink);

        if(lk.isEmpty()){
            throw new IllegalArgumentException("Link not found");
        }

        return lk.get();
    }


}
