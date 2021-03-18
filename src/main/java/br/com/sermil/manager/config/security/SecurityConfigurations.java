package br.com.sermil.manager.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.sermil.manager.repository.UserRepository;

@EnableWebSecurity //HABILITEI O MÓDULO SPRING SECURITY NA MINHA APLICAÇÃO;
@Configuration     //NO START DO SISTEMA, O SPRING RECONHECE QUE ESSA CLASSE É DE CONFIGURAÇÃO;
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{ 
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired //INJETANDO UMA DEPENDÊNCIA.
	private AuthenticationService authenticationService;
	
	//CONFIGURAÇÕES DE AUTENTICAÇÃO
	@Override
	@Bean //O SPRING IDENTIFICA QUE O MÉTODO DEVOLVE O AUTHENTICATIONMANAGER
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//CONFIGURAÇÕES DE AUTORIZAÇÃO
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { //CONFIGURAÇÕES DE AUTENTICAÇÃO - CONTROLE DE ACESSO
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { //CONFIGURAÇÕES DE AUTORIZAÇÃO - QUEM PODE ACESSAR URL, ETC.

		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/login").permitAll()                   //PERMITINDO LOGIN;
		.antMatchers(HttpMethod.POST, "/register").permitAll()                //PERMITINDO REGISTRO;   
		.antMatchers(HttpMethod.GET, "/").permitAll()    	                  //PERMITINDO HOME;
		.antMatchers(HttpMethod.GET, "/users").hasRole("USUARIO")   	      //PERMITINDO HOME;
		
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //USAR TOKEN NO LUGAR DE INICIAR UMA SESSÃO;
		.and().addFilterBefore(new TokenAuthenticationFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception { //CONFIGURAÇÕES DE RECURSOS ESTÁTICOS - REQUISIÇÕES DE JS, CSS, IMAGENS, ETC.
		//IGNORA A VERIFICAÇÃO DE SEGURANÇA DOS ENDEREÇOS CONFIGURADOS ABAIXO:
		web.ignoring().antMatchers("/**.html", "/v3/api-docs/**", "/webjars/**", "/configuration/**", "/swagger-resources", "/swagger-ui/**", "/swagger-ui");
	}
}
