package br.com.textoit.goldenraspberryawards.resources.impl;

import br.com.textoit.goldenraspberryawards.dto.MovieRequestDTO;
import br.com.textoit.goldenraspberryawards.dto.MovieResponseDTO;
import br.com.textoit.goldenraspberryawards.resources.MovieResource;
import br.com.textoit.goldenraspberryawards.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/movies/v1")
public class MovieResourceImpl implements MovieResource {

    private final MovieService movieService;

    public MovieResourceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public CompletableFuture<ResponseEntity<MovieResponseDTO>> create(final MovieRequestDTO movieRequestDTO) {
        return CompletableFuture.supplyAsync(() ->{
            MovieResponseDTO movie = this.movieService.createMovie(movieRequestDTO);
            return ResponseEntity.created(URI.create(String.format("/api/movies/v1/%s", movie.id()))).body(movie);
        });
    }

    @Override
    public CompletableFuture<ResponseEntity<MovieResponseDTO>> update(final Long id, final MovieRequestDTO movieRequestDTO) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(this.movieService.update(id, movieRequestDTO)));
    }

    @Override
    public void delete(final Long id) {
        this.movieService.delete(id);
    }

    @Override
    public CompletableFuture<ResponseEntity<MovieResponseDTO>> findMovie(Long id) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(this.movieService.findMovie(id)));
    }

}
