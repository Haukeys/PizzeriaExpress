package it.itsacademy.pizzeriaexpress.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

// CHANGEMENT : Ajout de @Component pour que Spring détecte cette classe comme un Bean
// et puisse gérer l'injection du @Value et l'injecter dans les autres classes.
@Component
public class AuthenticationService {//apiservice

    //ça ne change pas
    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY"; //key termine nella documentazione

    // CHANGEMENT : Utilisation de @Value pour charger la clé dynamiquement.
    // La valeur n'est plus écrite en dur ("Baeldung").
    @Value("${app.securityapi-key}")
    private String AUTH_TOKEN;  //secret termine per il token
    // CHANGEMENT : Le mot-clé "static" a été SUPPRIMÉ.
    // La méthode est maintenant une méthode d'instance, ce qui lui donne le droit
    // d'accéder à la variable d'instance "AUTH_TOKEN" injectée par Spring.

    public  Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);

        // AUTH_TOKEN n'est plus rouge ici car la méthode n'est plus statique !
        if (apiKey == null || !apiKey.equals(AUTH_TOKEN)) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }

}
