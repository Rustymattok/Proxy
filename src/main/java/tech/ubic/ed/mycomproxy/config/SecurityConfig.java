package tech.ubic.ed.mycomproxy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import tech.ubic.ed.security.configuration.EdAuthConfiguration;

@Configuration
@EnableOAuth2Client
public class SecurityConfig extends EdAuthConfiguration {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http
            .csrf().disable()
            .authorizeRequests()
//            .antMatchers("/find/**").authenticated()
            .antMatchers("/**").permitAll()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

}