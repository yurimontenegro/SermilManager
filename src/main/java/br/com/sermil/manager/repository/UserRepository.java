package br.com.sermil.manager.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sermil.manager.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
	Page<User> findByEmail(String email, Pageable pageable); //IRÁ UTILIZAR COMO PARÂMETRO O EMAIL DO USUÁRIO
}
