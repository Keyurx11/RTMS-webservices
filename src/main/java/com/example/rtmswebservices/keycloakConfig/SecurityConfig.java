//package com.example.rtmswebservices.keycloakConfig;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    String jwkSetUri = "http://keycloak:8080/auth/realms/RTMS/protocol/openid-connect/certs";
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//                .requestMatchers(HttpMethod.DELETE, "/**").hasRole("RTMS-admin")
//                .requestMatchers(HttpMethod.GET, "/api/taxcategory/**", "/api/taxrate/**").hasAnyRole("RTMS-admin", "RTMS-officer")
//                .requestMatchers(HttpMethod.GET, "/**").hasAnyRole("RTMS-admin", "RTMS-officer", "RTMS-user")
//                .requestMatchers(HttpMethod.POST, "/**").hasAnyRole("RTMS-admin", "RTMS-officer", "RTMS-user")
//                .requestMatchers(HttpMethod.PUT, "/**").hasAnyRole("RTMS-admin", "RTMS-officer", "RTMS-user")
//                .anyRequest().authenticated();
//
//        http.oauth2ResourceServer().jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()));
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        return http.build();
//    }
//
//    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
//        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
//        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmConverter());
//        return jwtConverter;
//    }
//
//    @Bean
//    JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
//    }
//}
