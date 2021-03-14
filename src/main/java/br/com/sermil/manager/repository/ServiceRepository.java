package br.com.sermil.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sermil.manager.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {

}
