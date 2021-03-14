package br.com.sermil.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sermil.manager.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
