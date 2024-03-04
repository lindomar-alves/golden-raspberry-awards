package br.com.textoit.goldenraspberryawards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MovieResponseDTO(
        Long id,
        @NotNull(message = "year is mandatory")
        Integer year,
        @NotEmpty(message = "title is mandatory")
        String title,
        @NotNull(message = "winner is mandatory")
        Boolean winner,
        List<ProducerDTO> producers,
        @NotNull(message = "studio is mandatory")
        List<StudioDTO> studios
)
{}
