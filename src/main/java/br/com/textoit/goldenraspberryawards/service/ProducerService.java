package br.com.textoit.goldenraspberryawards.service;

import br.com.textoit.goldenraspberryawards.domain.Movie;
import br.com.textoit.goldenraspberryawards.domain.Producer;
import br.com.textoit.goldenraspberryawards.domain.repository.ProducerRepository;
import br.com.textoit.goldenraspberryawards.dto.ProducerStats;
import br.com.textoit.goldenraspberryawards.dto.GoldenRaspberryAwardsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ProducerService {

    private final ProducerRepository producerRepository;

    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public GoldenRaspberryAwardsDTO getProducerIntervals() {
        List<Producer> producers = getAllProducers();

        List<ProducerStats> producerStatsList = producers.stream()
                .map(this::calculateInterval)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(ProducerStats::interval))
                .toList();

        final int middle = producerStatsList.size() / 2;

        final List<ProducerStats> minInterval = producerStatsList.subList(0, middle)
                .stream().sorted(Comparator.comparingInt(ProducerStats::followingWin))
                .toList();
        final List<ProducerStats> maxInterval = producerStatsList.subList(middle, producerStatsList.size())
                .stream().sorted(Comparator.comparingInt(ProducerStats::followingWin))
                .toList();

        return new GoldenRaspberryAwardsDTO(minInterval, maxInterval);
    }

    private ProducerStats calculateInterval(Producer producer) {
        if (producer.getMovies().size() == 1) {
            return null;
        }
        List<Movie> movies = producer.getMovies();
        movies.sort(Comparator.comparingInt(Movie::getReleaseYear));

        int interval = 0;
        int previousWin = 0;
        int followingWin = 0;

        for (int i = 0; i < movies.size() - 1; i++) {
            Movie current = movies.get(i);
            Movie next = movies.get(i + 1);
            interval = next.getReleaseYear() - current.getReleaseYear();
            previousWin = current.getReleaseYear();
            followingWin = next.getReleaseYear();
        }

        return new ProducerStats(producer.getName(), interval, previousWin, followingWin);
    }

    private List<Producer> getAllProducers() {
        return this.producerRepository.findAllWithMovies();
    }

    public List<Producer> getProducerByIds(final List<Long> idsProducer) {
        final List<Producer> producerList = this.producerRepository.findByIds(idsProducer);
        if (producerList.isEmpty()){
            throw new NotFoundException("Producers not found");
        }
        return producerList;
    }
}



