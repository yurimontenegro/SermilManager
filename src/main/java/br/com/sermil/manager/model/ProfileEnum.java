package br.com.sermil.manager.model;

import lombok.Getter;

@Getter
public enum ProfileEnum {
	
	ROLE_COLABORADOR("ROLE_COLABORADOR"),
	ROLE_USUARIO("ROLE_USUARIO"),
	ROLE_ADMINISTRADOR("ROLE_ADMINISTRADOR");

	ProfileEnum(String string) {
	}
}
