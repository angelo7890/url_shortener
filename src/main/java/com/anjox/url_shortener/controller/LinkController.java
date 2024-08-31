package com.anjox.url_shortener.controller;
import com.anjox.url_shortener.entities.LinkDTO;
import com.anjox.url_shortener.service.LinkService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping
public class LinkController {
    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }
    @PostMapping("/salvar")
    public ResponseEntity<?> saveLink(@RequestBody LinkDTO linkDTO) {
        try {
            var response = linkService.save(linkDTO);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    @GetMapping("{nomeLink}")
    public ResponseEntity<?> link(@PathVariable("nomeLink") String nomeLink, HttpServletResponse response) {

        try{
            var url = linkService.redirect(nomeLink);
            response.sendRedirect(url);
            return ResponseEntity.status(301).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
