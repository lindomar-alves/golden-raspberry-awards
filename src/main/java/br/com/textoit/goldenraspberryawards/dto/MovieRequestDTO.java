package br.com.textoit.goldenraspberryawards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record MovieRequestDTO(
        Long id,
        @NotNull(message = "year is mandatory")
        Integer year,
        @NotEmpty(message = "title is mandatory")
        String title,
        @NotNull(message = "winner is mandatory")
        Boolean winner
)
{}
