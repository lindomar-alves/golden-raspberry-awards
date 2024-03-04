package br.com.textoit.goldenraspberryawards.dto;

import jakarta.validation.constraints.NotEmpty;

public record StudioDTO(
        Long id,
        @NotEmpty(message = "name is mandatory")
        String name
) {
}
