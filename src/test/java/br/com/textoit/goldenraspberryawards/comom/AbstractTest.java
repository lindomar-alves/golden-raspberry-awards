package br.com.textoit.goldenraspberryawards.comom;

import br.com.textoit.goldenraspberryawards.GoldenRaspberryAwardsApplication;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;

@ActiveProfiles("test")
@SpringBootTest(classes = GoldenRaspberryAwardsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = GoldenRaspberryAwardsApplication.class)
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractTest {

    @LocalServerPort
    private int port;

    @BeforeAll
    public void setup() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
        enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
