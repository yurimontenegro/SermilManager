package br.com.sermil.manager.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sermil.manager.model.User;
import br.com.sermil.manager.repository.UserRepository;

@Service //É UM SERVIÇO DO SPRING.
public class AuthenticationService implements UserDetailsService{

	@Autowired
	private UserRepository repository; //CHAMEI O REPOSITORY
	
	@Override
	//NO LOGIN, O SPRING VAI ENTENDER QUE ESSA CLASSE É DE IDENTIFICAÇÃO DO USUÁRIO.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		Optional <User> user = repository.findByEmail(username);
		if (user.isPresent() && user.get().isActive()) {
			return user.get();
		} 
		throw new UsernameNotFoundException("DADOS INVÁLIDOS");
		
	}

}
