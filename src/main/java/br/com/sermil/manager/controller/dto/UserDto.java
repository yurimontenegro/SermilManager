package br.com.sermil.manager.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.sermil.manager.model.ProfileEnum;
import br.com.sermil.manager.model.User;
import lombok.Getter;

@Getter
public class UserDto {
	
	private Long id;
	private String name;
	private String email;
	private ProfileEnum profile;
	private LocalDateTime creationDate;
	private boolean contaAtiva;

	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.creationDate = user.getCreationDate();
		this.contaAtiva = user.isActive();
		this.profile = user.getProfile();
	}

	public static Page<UserDto> convert(Page<User> users) {
		return users.map(UserDto::new); // ::new CHAMA O CONSTRUTOR;
	}

}
