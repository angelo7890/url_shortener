package com.anjox.url_shortener.entities;

import java.time.LocalDateTime;

public record LinkDTO(
        String linkorignal,
        String nomelink
) {
}
