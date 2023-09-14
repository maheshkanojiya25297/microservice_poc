package ExelFileUploadApachePoI.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(title = "Jwt-Token_Authentication"),
        servers = {@Server(
                description = "Swagger Url",
                url = "http://localhost:8081/"
        )}
)
public class SwaggerConfig {

}
