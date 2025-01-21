package shopping.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shopping Cart API")
                        .version("1.0")
                        .description("Documentación de la API para la gestión de Carrito de Compras para Indra")
                        .contact(new Contact()
                                .name("Jonatan Echeverry")
                                .email("jonechev1@gmail.com")
                                .url("")
                        )
                );
    }

}