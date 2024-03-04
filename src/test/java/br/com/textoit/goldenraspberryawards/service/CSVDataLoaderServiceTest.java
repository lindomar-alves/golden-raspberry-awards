package br.com.textoit.goldenraspberryawards.service;

import br.com.textoit.goldenraspberryawards.domain.Movie;
import br.com.textoit.goldenraspberryawards.domain.Producer;
import br.com.textoit.goldenraspberryawards.domain.Studio;
import br.com.textoit.goldenraspberryawards.domain.repository.MovieRepository;
import br.com.textoit.goldenraspberryawards.domain.repository.ProducerRepository;
import br.com.textoit.goldenraspberryawards.domain.repository.StudioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class CSVDataLoaderServiceTest {

    @Autowired
    private CSVDataLoaderService csvDataLoaderService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @DisplayName("Realizar leitura csv e adicionar na base")
    @Test
    void testRun() {
        // Execute o método run
        csvDataLoaderService.loadData("classpath:movielist.csv");

        // Verifique se os filmes foram salvos corretamente no banco de dados
        List<Movie> movies = movieRepository.findAll();
        assertNotNull(movies);
        assertFalse(movies.isEmpty());

        // Verifique se os estúdios foram salvos corretamente no banco de dados
        List<Studio> studios = studioRepository.findAll();
        assertNotNull(studios);
        assertFalse(studios.isEmpty());

        // Verifique se os produtores foram salvos corretamente no banco de dados
        List<Producer> producers = producerRepository.findAll();
        assertNotNull(producers);
        assertFalse(producers.isEmpty());
    }

}