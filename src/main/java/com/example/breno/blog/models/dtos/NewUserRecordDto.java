package com.example.breno.blog.models.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record NewUserRecordDto(@NotBlank String userName, @NotBlank String password) {
}
