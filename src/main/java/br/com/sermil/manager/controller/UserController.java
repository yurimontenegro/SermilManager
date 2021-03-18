package br.com.sermil.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sermil.manager.controller.dto.UserDto;
import br.com.sermil.manager.model.User;
import br.com.sermil.manager.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping // LISTA OS USUÁRIOS;
	public Page<UserDto> list(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable paginacao) {

		Page<User> users = userRepository.findAll(paginacao);
		return UserDto.convert(users);
	}
}