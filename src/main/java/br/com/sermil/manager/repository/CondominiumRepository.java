package br.com.sermil.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sermil.manager.model.Condominium;

public interface CondominiumRepository extends JpaRepository<Condominium, Long> {

}
