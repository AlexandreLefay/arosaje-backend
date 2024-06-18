package fr.epsi.mspr.arosaje.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("oauth2Scheme",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .flows(new OAuthFlows()
                                                .authorizationCode(new OAuthFlow()
                                                        .authorizationUrl("https://dev-xg6xqpzynp8fjyur.us.auth0.com/authorize")
                                                        .tokenUrl("https://dev-xg6xqpzynp8fjyur.us.auth0.com/oauth/token")
                                                        .scopes(new Scopes().addString("openid", "OpenID Connect scope"))
                                                )
                                        )
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("oauth2Scheme"));
    }
}
