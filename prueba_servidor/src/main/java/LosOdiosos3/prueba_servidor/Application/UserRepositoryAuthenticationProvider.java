package LosOdiosos3.prueba_servidor.Application;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		// se coge el usuario con dicho nombre
		List <User> usur = userRepository.findByName(auth.getName());
		
		// se comprueba que se ha cogido al menos un usuario
		if (usur.size() < 1) {
			throw new BadCredentialsException("User not found");
		}
		
		// se coge el usuario y la contraseña
		User user = usur.get(0);
		String password = (String) auth.getCredentials();
		
		// se comprueba que la contraseña introducida concuerda con la contraseña cifrada
		if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
			throw new BadCredentialsException("Wrong password");
		}
		
		// se le aportan sus roles
		List<GrantedAuthority> roles = new ArrayList<>();
		for (String role : user.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role));
		}
		
		// se retorna el objeto con nombre, contraseña y roles
		return new UsernamePasswordAuthenticationToken(user.getName(), password, roles);
	}
	
	@Override
	public boolean supports(Class<?> authenticationObject) {
		return true;
	}
}
