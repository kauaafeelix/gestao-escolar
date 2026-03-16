package com.weg.centroweg.gestaoescolar.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestão Escolar API")
                        .description("API REST para gerenciamento de instituições de ensino. Permite gerenciar alunos, professores, cursos, turmas, aulas e notas.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Gestão Escolar")
                                .url("https://github.com/kauaafeelix/gestao-escolar")));
    }
}
