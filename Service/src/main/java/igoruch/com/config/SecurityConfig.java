package igoruch.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


/**
 * Created by Igoruch on 05.01.2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select login,password ,'true' as enabled from user where login=?")
                .authoritiesByUsernameQuery(
                        "select login,'USER' from user where login=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin/**").access("isAuthenticated()")
                .antMatchers("/my/**").access("isAuthenticated()")
                .antMatchers("/users/**").access("isAuthenticated()")
                .antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
                .and().formLogin().loginPage("/login").defaultSuccessUrl("//localhost:63343/WebClient/view/Manager.html?_ijt=qn724g5lr0io77i2hpkg3udcp3", true).failureUrl("/login?error").permitAll().and().headers().disable();

        //Cross Site Request Forgery
        http.csrf().disable();



    }
}
