package LosOdiosos3.prueba_servidor.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/article").permitAll();
        http.authorizeRequests().antMatchers("/company").permitAll();
        http.authorizeRequests().antMatchers("/company_list").permitAll();
        http.authorizeRequests().antMatchers("/event_calendar").permitAll();
        http.authorizeRequests().antMatchers("/event").permitAll();
        http.authorizeRequests().antMatchers("/game").permitAll();
        http.authorizeRequests().antMatchers("/game_list").permitAll();
        http.authorizeRequests().antMatchers("/new_user").permitAll();
        http.authorizeRequests().antMatchers("/search").permitAll();

        // paginas referentes al usuario logeado
        http.authorizeRequests().antMatchers("/my_lists").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/profile").hasAnyRole("USER");
        // paginas referentes al administrador
        http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/addGame").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/addEvent").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/addCompany").hasAnyRole("ADMIN");
        
        // login settings ...
        // en caso de acceder a una página errónea, se le dirige a login, y acto seguido a index
        http.formLogin().loginPage("/login");
        // atributos de cara a poder iniciar sesion
        http.formLogin().usernameParameter("name");
        http.formLogin().passwordParameter("password");
        // en caso del correcto inicio de sesion
        http.formLogin().defaultSuccessUrl("/login/true");
        // en caso del incorrecto inicio de sesion
        http.formLogin().failureUrl("/login/false");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);       
    }
}
