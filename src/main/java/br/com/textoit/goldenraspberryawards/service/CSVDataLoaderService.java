package br.com.textoit.goldenraspberryawards.service;

import br.com.textoit.goldenraspberryawards.domain.Movie;
import br.com.textoit.goldenraspberryawards.domain.Producer;
import br.com.textoit.goldenraspberryawards.domain.Studio;
import br.com.textoit.goldenraspberryawards.domain.repository.ProducerRepository;
import br.com.textoit.goldenraspberryawards.domain.repository.StudioRepository;
import br.com.textoit.goldenraspberryawards.domain.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CSVDataLoaderService {

    private final ResourceLoader resourceLoader;
    private final MovieRepository movieRepository;
    private final StudioRepository studioRepository;
    private final ProducerRepository producerRepository;

    private final Map<String, Producer> producerMap = new HashMap<>();
    private final Map<String, Studio> studiosMap = new HashMap<>();
    private final Map<String, Movie> movieMap = new HashMap<>();

    public CSVDataLoaderService(final ResourceLoader resourceLoader,
                                final MovieRepository movieRepository,
                                final StudioRepository studioRepository,
                                final ProducerRepository producerRepository) {
        this.resourceLoader = resourceLoader;
        this.movieRepository = movieRepository;
        this.studioRepository = studioRepository;
        this.producerRepository = producerRepository;
    }

    @Transactional
    public void loadData(final String csvFilePath) {

        try {
            Resource resource = resourceLoader.getResource(csvFilePath);
            InputStreamReader reader = new InputStreamReader(resource.getInputStream());
            BufferedReader br = new BufferedReader(reader);
            CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader());

            for (CSVRecord csvRecord : csvParser) {
                processCSVRecord(csvRecord);
            }

        } catch (Exception e) {
            log.error("Error loading CSV data", e);
        }
    }

    private void processCSVRecord(CSVRecord csvRecord) {
        String year = csvRecord.get(0);
        String title = csvRecord.get(1);
        String[] studios = csvRecord.get(2).split(", ");
        String[] producers = csvRecord.get(3).split("\\s+and\\s+|,\\s*");
        String winner = csvRecord.get(4);

        if (title.isEmpty()) {
            return;
        }

        Movie movie = saveMovie(year, title, winner);
        saveStudiosAndProducers(movie, studios, producers);
    }

    private Movie saveMovie(String year, String title, String winner) {
        Movie movie = getOrCreateMovie(title, year, winner);
        return movieRepository.save(movie);
    }

    private void saveStudiosAndProducers(Movie movie, String[] studios, String[] producers) {
        for (String studioName : studios) {
            Studio studio = getOrCreateStudio(studioName);
            studio.addMovie(movie);
            movie.addStudio(studio);
            studioRepository.save(studio);
        }

        for (String producerName : producers) {
            Producer producer = getOrCreateProducer(producerName.replaceFirst("^and\\s*", ""));
            producer.addMovie(movie);
            movie.addProducer(producer);
            producerRepository.save(producer);
        }
    }

    private Movie getOrCreateMovie(String movieTitle, String year, String winner) {
        return movieMap.computeIfAbsent(movieTitle, title -> Movie.builder()
                .releaseYear(Integer.parseInt(year))
                .title(title)
                .winner("yes".equals(winner))
                .build());
    }

    private Studio getOrCreateStudio(String studioName) {
        return studiosMap.computeIfAbsent(studioName, name -> Studio.builder().name(name).build());
    }

    private Producer getOrCreateProducer(String producerName) {
        return producerMap.computeIfAbsent(producerName, name -> Producer.builder().name(name).build());
    }

}
