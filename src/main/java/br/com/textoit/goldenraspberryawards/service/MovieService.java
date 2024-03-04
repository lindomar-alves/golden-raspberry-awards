package br.com.textoit.goldenraspberryawards.service;

import br.com.textoit.goldenraspberryawards.domain.Movie;
import br.com.textoit.goldenraspberryawards.domain.Producer;
import br.com.textoit.goldenraspberryawards.domain.Studio;
import br.com.textoit.goldenraspberryawards.domain.repository.MovieRepository;
import br.com.textoit.goldenraspberryawards.domain.repository.ProducerRepository;
import br.com.textoit.goldenraspberryawards.domain.repository.StudioRepository;
import br.com.textoit.goldenraspberryawards.dto.MovieRequestDTO;
import br.com.textoit.goldenraspberryawards.dto.MovieResponseDTO;
import br.com.textoit.goldenraspberryawards.mapper.MovieMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final ProducerRepository producerRepository;
    private final StudioRepository studioRepository;

    public MovieService(final MovieRepository movieRepository,
                        final MovieMapper movieMapper,
                        final ProducerRepository producerRepository,
                        final StudioRepository studioRepository) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.producerRepository = producerRepository;
        this.studioRepository = studioRepository;
    }

    public MovieResponseDTO createMovie(final MovieRequestDTO movieRequestDTO) {
        final Movie movie = this.movieRepository.save(this.movieMapper.to(movieRequestDTO));
        return this.movieMapper.to(movie);
    }

    public MovieResponseDTO update(final Long id, final MovieRequestDTO movieRequestDTO) {
        Movie movie = this.findById(id);

        final Movie movieUpdated = this.movieRepository.save(movie.withTitle(movieRequestDTO.title())
                .withWinner(movieRequestDTO.winner())
                .withReleaseYear(movieRequestDTO.year()));

        return this.movieMapper.to(movieUpdated);

    }

    public void delete(final Long id) {
        Movie movie = this.findById(id);
        List<Producer> producers = this.producerRepository.findByIds(movie.getProducers().stream().map(Producer::getId).toList());
        List<Studio> studios = this.studioRepository.findByIds(movie.getStudios().stream().map(Studio::getId).toList());

        producers.forEach(producer -> producer.getMovies().removeIf(movie1 -> movie1.getId().equals(movie.getId())));
        studios.forEach(studio -> studio.getMovies().removeIf(movie1 -> movie1.getId().equals(movie.getId())));

        this.producerRepository.saveAll(producers);
        this.studioRepository.saveAll(studios);

        this.movieRepository.deleteById(id);
    }

    private Movie findById(final Long id) {
        return this.movieRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Movie not found"));
    }

    public MovieResponseDTO findMovie(final Long id) {
        Movie movie = this.findById(id);

        List<Producer> producers = this.producerRepository.findByIMovie(id);
        List<Studio> studios = this.studioRepository.findByIMovie(id);

        return this.movieMapper.toDto(movie.withProducers(producers).withStudios(studios));
    }

}
