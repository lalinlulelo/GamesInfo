package LosOdiosos3.prueba_servidor.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/index").permitAll();
        http.authorizeRequests().antMatchers("/article").permitAll();
        http.authorizeRequests().antMatchers("/company").permitAll();
        http.authorizeRequests().antMatchers("/company_list").permitAll();
        http.authorizeRequests().antMatchers("/event_calendar").permitAll();
        http.authorizeRequests().antMatchers("/event").permitAll();
        http.authorizeRequests().antMatchers("/game").permitAll();
        http.authorizeRequests().antMatchers("/game_list").permitAll();
        http.authorizeRequests().antMatchers("/new_user").permitAll();
        http.authorizeRequests().antMatchers("/search").permitAll();

        //Private pages (all other pages)
        http.authorizeRequests().antMatchers("/my_lists").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/profile").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");

        //Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("name");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/login/true");
        http.formLogin().failureUrl("/login/false");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // Database authentication provider
        auth.authenticationProvider(authenticationProvider);       
    }
}
