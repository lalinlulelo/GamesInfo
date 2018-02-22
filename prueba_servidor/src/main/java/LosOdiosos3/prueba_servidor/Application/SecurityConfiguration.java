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
        http.authorizeRequests().antMatchers("/index.html").permitAll();
        http.authorizeRequests().antMatchers("/article.html").permitAll();
        http.authorizeRequests().antMatchers("/company.html").permitAll();
        http.authorizeRequests().antMatchers("/company_list.html").permitAll();
        http.authorizeRequests().antMatchers("/event_calendar.html").permitAll();
        http.authorizeRequests().antMatchers("/event.html").permitAll();
        http.authorizeRequests().antMatchers("/game.html").permitAll();
        http.authorizeRequests().antMatchers("/game_list.html").permitAll();
        http.authorizeRequests().antMatchers("/new_user.html").permitAll();
        http.authorizeRequests().antMatchers("/search.html").permitAll();

        // Private pages (all other pages)
        http.authorizeRequests().antMatchers("/my_lists.html").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/profile.html").hasAnyRole("USER");

        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("name");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/index.html");
        http.formLogin().failureUrl("/login");

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
    
	@Override
	public void configure(WebSecurity web) throws Exception {

	}

}
