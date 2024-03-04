package br.com.textoit.goldenraspberryawards.resources;

import br.com.textoit.goldenraspberryawards.dto.GoldenRaspberryAwardsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

@Tag(name = "GolderRapsberyAwards", description = "Api GolderRapsberyAwards")
public interface GolderRapsberyAwardsResource {

    @Operation(
            summary = "Find GoldenRaspberryAwards",
            description = "Get the producer with the longest gap between two consecutive awards, and the one who got two awards the fastest")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    CompletableFuture<ResponseEntity<GoldenRaspberryAwardsDTO>>getGoldenRaspberryAwards();

}
