package br.com.sermil.manager.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.sermil.manager.model.User;
import br.com.sermil.manager.repository.UserRepository;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UserRepository repository;
	
	public TokenAuthenticationFilter(TokenService tokenService, UserRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = retrieveToken(request);  // RETRIEVE = RECUPERAR
		boolean valid = tokenService.isValidToken(token);
		if (valid) {
			authenticateClient(token);
		}
		
		filterChain.doFilter(request, response); 
	}

	private void authenticateClient(String token) {
		Long userId = tokenService.getUserId(token);    //PEGUEI O ID DO TOKEN;
		User user = repository.findById(userId).get();  //RECUPEREI O OBJETO USUÁRIO USANDO O ID
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication); //FORÇA A AUTENTICAÇÃO;
	}

	private String retrieveToken(HttpServletRequest request) { //RECUPERAR TOKEN
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) { //SE O TOKEN FOR NULO, EM BRANCO OU NÃO COMEÇAR COM BEARER:
		return null;
	}
		
	return token.substring(7, token.length()); //COMEÇA A PEGAR O TOKEN A PARTIR DO CARACTERE 7;
	}

}
