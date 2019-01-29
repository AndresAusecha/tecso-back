package coop.tecso.examen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/movimientos-rest-repo/*").denyAll()
                .antMatchers(HttpMethod.DELETE, "/movimientos-rest-repo/*").denyAll()
                .antMatchers(HttpMethod.POST, "/movimientos-rest-repo").denyAll()
                .antMatchers(HttpMethod.POST, "/cuentas-rest-repo").denyAll();
    }
}
