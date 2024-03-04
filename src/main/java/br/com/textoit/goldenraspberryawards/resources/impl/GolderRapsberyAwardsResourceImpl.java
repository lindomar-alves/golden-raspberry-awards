package br.com.textoit.goldenraspberryawards.resources.impl;

import br.com.textoit.goldenraspberryawards.dto.GoldenRaspberryAwardsDTO;
import br.com.textoit.goldenraspberryawards.resources.GolderRapsberyAwardsResource;
import br.com.textoit.goldenraspberryawards.service.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/api/golder-rapsbery-awards/v1")
public class GolderRapsberyAwardsResourceImpl implements GolderRapsberyAwardsResource {

    private final ProducerService producerService;

    public GolderRapsberyAwardsResourceImpl(ProducerService producerService) {
        this.producerService = producerService;
    }

    @Override
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public CompletableFuture<ResponseEntity<GoldenRaspberryAwardsDTO>> getGoldenRaspberryAwards() {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(producerService.getProducerIntervals()));
    }
}
