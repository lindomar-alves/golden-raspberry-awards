package br.com.textoit.goldenraspberryawards.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info =
    @Info(title = "API-Golden Raspberry Awards", version = "1.0", description = "Documentation", contact =
        @Contact(name = "ti-texto", email = "teste@titexto.com.br"))
)
public class DocOpenAPIConfig {}