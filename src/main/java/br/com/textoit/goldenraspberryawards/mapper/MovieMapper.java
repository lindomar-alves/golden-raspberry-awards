package br.com.textoit.goldenraspberryawards.mapper;

import br.com.textoit.goldenraspberryawards.domain.Movie;
import br.com.textoit.goldenraspberryawards.dto.MovieRequestDTO;
import br.com.textoit.goldenraspberryawards.dto.MovieResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {

    @Mapping(source = "year", target = "releaseYear")
    Movie to(MovieRequestDTO movieRequestDTO);

    @Mapping(source = "releaseYear", target = "year")
    @Mapping(target = "producers", ignore = true)
    @Mapping(target = "studios", ignore = true)
    MovieResponseDTO to(Movie movie);

    @Mapping(source = "releaseYear", target = "year")
    MovieResponseDTO toDto(Movie movie);

}
