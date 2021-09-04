package com.eliarlan.edevmoneyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eliarlan.edevmoneyapi.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	public List <Cidade> findByEstadoCodigo(Long estadoCodigo); 
}
