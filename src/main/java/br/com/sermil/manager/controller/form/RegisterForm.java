package br.com.sermil.manager.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sun.istack.NotNull;

import br.com.sermil.manager.model.User;
import lombok.Getter;

public class RegisterForm {

	@NotNull
	@NotEmpty
	@Getter
	private String name;

	@Email
	@Getter
	private String email;

	@NotNull
	@NotEmpty
	@Getter
	protected String password;

	public User convert() {
		User user = new User(name, email, new BCryptPasswordEncoder().encode(password));
		return user;
	}

}
