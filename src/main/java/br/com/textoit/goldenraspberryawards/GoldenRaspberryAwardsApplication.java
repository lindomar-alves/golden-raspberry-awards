package br.com.textoit.goldenraspberryawards;

import br.com.textoit.goldenraspberryawards.service.CSVDataLoaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class GoldenRaspberryAwardsApplication implements CommandLineRunner {

	private final CSVDataLoaderService csvDataLoaderService;

    public GoldenRaspberryAwardsApplication(CSVDataLoaderService csvDataLoaderService) {
        this.csvDataLoaderService = csvDataLoaderService;
    }

    public static void main(String[] args) {
		SpringApplication.run(GoldenRaspberryAwardsApplication.class, args);
	}

	@Override
	public void run(String... args) {
		csvDataLoaderService.loadData("classpath:movielist.csv");
	}
}
