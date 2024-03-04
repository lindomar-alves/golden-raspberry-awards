package br.com.textoit.goldenraspberryawards.resources;

import br.com.textoit.goldenraspberryawards.dto.MovieRequestDTO;
import br.com.textoit.goldenraspberryawards.dto.MovieResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.concurrent.CompletableFuture;

@Tag(name = "Movie", description = "Api Movie")
public interface MovieResource {

    @Operation(summary = "Save new movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    CompletableFuture<ResponseEntity<MovieResponseDTO>> create(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request movie")
                                                               @RequestBody @Valid MovieRequestDTO movieRequestDTO);

    @Operation(summary = "update movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    CompletableFuture<ResponseEntity<MovieResponseDTO>> update(@PathVariable("id") Long id,
                                                               @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request movie")
                                                               @RequestBody @Valid MovieRequestDTO movieRequestDTO);

    @Operation(summary = "delete movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable("id") Long id);

    @Operation(summary = "find movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    CompletableFuture<ResponseEntity<MovieResponseDTO>> findMovie(@PathVariable("id") Long id);
}
