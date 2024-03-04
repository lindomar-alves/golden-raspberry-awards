package br.com.textoit.goldenraspberryawards.resources.impl;

import br.com.textoit.goldenraspberryawards.comom.AbstractTest;
import br.com.textoit.goldenraspberryawards.dto.MovieRequestDTO;
import br.com.textoit.goldenraspberryawards.dto.MovieResponseDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

class MovieResourceImplTest extends AbstractTest {

    private static final String PARH_MOVIE = "/api/movies/v1";

    @Test
    @DisplayName("Sucesso - Deve criar um novo filme")
    void should_created_new_movie() {

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(new MovieRequestDTO(null, 1980, "Gremlins", false))
                .post(PARH_MOVIE)
                .then().assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", Matchers.notNullValue())
                .body("year", Matchers.is(1980))
                .body("title", Matchers.is("Gremlins"))
                .body("winner", Matchers.is(false));
    }

    @Test
    @DisplayName("Sucesso - Deve atualizar o filme")
    void should_update_movie() {

        MovieResponseDTO movieResponseDTO = given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(new MovieRequestDTO(null, 1980, "Gremlins", false))
                .post(PARH_MOVIE)
                .then().assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract().body().as(MovieResponseDTO.class);

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(new MovieRequestDTO(null, 1988, "Gremlins 2", false))
                .put(String.format(PARH_MOVIE+"/%s", movieResponseDTO.id()))
                .then().assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("id", Matchers.notNullValue())
                .body("year", Matchers.is(1988))
                .body("title", Matchers.is("Gremlins 2"))
                .body("winner", Matchers.is(false));
    }

    @Test
    @DisplayName("Sucesso - Deve deletar o filme")
    void should_delete_movie() {

        MovieResponseDTO movieResponseDTO = given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(new MovieRequestDTO(null, 1980, "Gremlins", false))
                .post(PARH_MOVIE)
                .then().assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract().body().as(MovieResponseDTO.class);

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .delete(String.format(PARH_MOVIE+"/%s", movieResponseDTO.id()))
                .then().assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Sucesso - Deve buscar os dados do filme, produtor, studio")
    void should_find_movie() {

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .get(String.format(PARH_MOVIE+"/%s", 3))
                .then().assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Erro - Deve tratar erro de requisição incorreta")
    void should_return_error_created_movie() {

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(new MovieRequestDTO(null, null, "Gremlins", false))
                .post(PARH_MOVIE)
                .then().assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}