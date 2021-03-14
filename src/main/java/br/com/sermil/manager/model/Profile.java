package br.com.sermil.manager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Profile implements GrantedAuthority{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) //ID GERADO AUTOMATICAMENTE;
	private Long id;
	@Getter @Setter
	private String name;

	@Override
	public String getAuthority() {
		return null;
	}

}
