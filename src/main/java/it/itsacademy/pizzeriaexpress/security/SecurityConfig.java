package it.itsacademy.pizzeriaexpress.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // CHANGEMENT : On déclare qu'on a besoin d'AuthenticationService.
    private AuthenticationService authenticationService;

    // CHANGEMENT : Injection par constructeur. Spring voit qu'on a besoin du service,
    // il va le chercher (grâce au @Component qu'on a mis SUR AUTHENTICATIONSERVICE) et l'injecte ici.
    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry
                        -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers(
                                "/clienti/**",
                                "/riders/**",
                                "/ordini/**"
                                ).authenticated()
                        .anyRequest().permitAll())//permet a ces endpoint de pas avoir besoin de authen
                //ici on implemente ce qu on veut mettre sous anthentification
                //.httpBasic(Customizer.withDefaults())peut causer des conflit car s execute en arriere plan en plus de celui de APIKEY
                .sessionManagement(httpSecuritySessionManagementConfigurer
                        -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // CHANGEMENT : Au lieu de faire "new AuthenticationFilter()",
                // on fait "new AuthenticationFilter(authenticationService)".
                // On transmet le service au filtre pour qu'il puisse l'utiliser.on lui passe la secret qu on a implementer.
                .addFilterBefore(new AuthenticationFilter(authenticationService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
