package br.com.sermil.manager.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.sermil.manager.model.Profile;
import br.com.sermil.manager.model.User;
import lombok.Getter;

public class UserDto {
	@Getter
	private Long id;
	@Getter
	private String name;
	@Getter
	private String email;
	@Getter
	private Profile profile;
	@Getter
	private LocalDateTime creationDate;
	@Getter
	private boolean contaAtiva;

	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.creationDate = user.getCreationDate();
		this.contaAtiva = user.isActive();
		//this.profile = user.getProfile();
	}

	public static Page<UserDto> convert(Page<User> users) {
		return users.map(UserDto::new); // ::new CHAMA O CONSTRUTOR;
	}

}
