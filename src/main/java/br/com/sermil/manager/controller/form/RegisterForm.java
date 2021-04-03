package br.com.sermil.manager.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sun.istack.NotNull;

import br.com.sermil.manager.model.User;
import lombok.Getter;

@Getter
public class RegisterForm {

	@NotNull
	@NotEmpty
	@Length(max = 20, min = 5)
	private String name;

	@Email
	private String email;

	@NotNull
	@NotEmpty
	protected String password;
	
	public static String Upper(String text) {
		text.toLowerCase();
		String[] partes = text.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < partes.length; i++) {
			String word = partes[i];
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			sb.append(" ").append(word);
		}
		return sb.toString().replaceFirst(" ", "");
	}

	public User convert() {
		return User.builder().
				name(Upper(name)).
				email(email).
				passwordUser(new BCryptPasswordEncoder().encode(password)).build();
	}
	
}
