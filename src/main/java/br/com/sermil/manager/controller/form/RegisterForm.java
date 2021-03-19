package br.com.sermil.manager.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sun.istack.NotNull;

import br.com.sermil.manager.model.User;
import lombok.Getter;

@Getter
public class RegisterForm {

	@NotNull
	@NotEmpty
	private String name;

	@Email
	private String email;

	@NotNull
	@NotEmpty
	protected String password;

	public User convert() {
		return User.builder().
				name(name).
				email(email).
				passwordUser(new BCryptPasswordEncoder().encode(password)).build();
	}

}
