package ru.ryazanov.ticktacktoe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.ryazanov.ticktacktoe.repository.PlayerRepository;
import ru.ryazanov.ticktacktoe.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {
    /**
     * Repo of Player entities.
     */
    private final PlayerRepository playerRepository;

    /**
     * Inject playerRepository using constructor.
     *
     * @param playerRepository - repository of players.
     */
    @Autowired
    public ConfigSecurity(final PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Configure global security.
     * Need more reads about Spring security.
     *
     * @param auth - manager authentications.
     * @throws Exception - can throw Exception, if details bad.
     */
    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(new UserDetailsServiceImpl(playerRepository))
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Config http security with fields Player.
     * Need more reads about Spring security.
     * @param http - HttpSecurity object.
     * @throws Exception - can throw Exception, if details bad.
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
