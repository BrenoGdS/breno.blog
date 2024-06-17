package com.example.breno.blog.models.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record NewPostRecordDto(@NotBlank String title, @NotBlank String content) {
}
