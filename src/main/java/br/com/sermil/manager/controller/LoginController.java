package br.com.sermil.manager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sermil.manager.config.security.TokenService;
import br.com.sermil.manager.controller.dto.TokenDto;
import br.com.sermil.manager.controller.form.LoginForm;
import br.com.sermil.manager.repository.UserRepository;

@CrossOrigin("http://localhost:4200/services/create")
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken loginData = form.convert(); // MÉTODO CONVERTER CRIADO PARA RECEBER EMAIL
																		// E SENHA;
		try {

			Authentication authentication = authManager.authenticate(loginData); // FAZER AUTENTICAÇÃO ATRAVÉS DOS
																					// DADOS DE LOGIN;
			String token = tokenService.generateToken(authentication);
			System.out.println(token);
			return ResponseEntity.ok(new TokenDto(token, "Bearer")); // DEVOLVE O TOKEN E O TIPO DELE PARA O CLIENTE;

		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build(); // DEVOLVE O BADREQUEST
		}
	}

}
