package br.com.textoit.goldenraspberryawards.resources.impl;

import br.com.textoit.goldenraspberryawards.resources.AbstractTest;
import br.com.textoit.goldenraspberryawards.service.CSVDataLoaderService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

class GolderRapsberyAwardsResourceImplTest extends AbstractTest {

    @Autowired
    private CSVDataLoaderService csvDataLoaderService;

    private static final String PATH_API_MOVIE = "/api/golder-rapsbery-awards/v1";

    @Test
    @DisplayName("Sucesso - Deve retornar as listas vazias")
    void should_return_list_empty_golder_rapsbery_awards() {

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .get(PATH_API_MOVIE)
                .then().assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("min", Matchers.hasSize(0))
                .body("max", Matchers.hasSize(0));
    }

    @Test
    @DisplayName("Sucesso - Deve retornar as listas com dados encontrados")
    void should_return_list_golder_rapsbery_awards() {
        csvDataLoaderService.loadData("classpath:movielistComplete.csv");

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .get(PATH_API_MOVIE)
                .then().assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("min", Matchers.hasSize(2))
                .body("max", Matchers.hasSize(2));
    }

}