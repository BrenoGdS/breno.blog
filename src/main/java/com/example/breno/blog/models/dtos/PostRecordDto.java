package com.example.breno.blog.models.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostRecordDto(UUID postId, @NotBlank UUID authorID, @NotBlank String title, @NotBlank String content, LocalDateTime creationDate) {
}
