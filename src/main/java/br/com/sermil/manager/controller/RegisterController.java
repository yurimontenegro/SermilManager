package br.com.sermil.manager.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sermil.manager.controller.dto.UserDto;
import br.com.sermil.manager.controller.form.RegisterForm;
import br.com.sermil.manager.repository.UserRepository;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	UserRepository usuarioRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> register(@RequestBody @Valid RegisterForm form, UriComponentsBuilder uriBuilder)
			throws Exception {
		if (usuarioRepository.findByEmail(form.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().build(); // SUBSTITUIR POR UMA EXCEPTION;
		}
		return new ResponseEntity<UserDto>(new UserDto(usuarioRepository.save(form.convert())),
				HttpStatus.CREATED);
	}

}
