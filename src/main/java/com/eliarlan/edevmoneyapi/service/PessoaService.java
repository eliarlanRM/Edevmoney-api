package com.eliarlan.edevmoneyapi.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.eliarlan.edevmoneyapi.model.Pessoa;
import com.eliarlan.edevmoneyapi.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	public PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = bucarPessoaPeloCodigo(codigo);
		
		pessoa.getContatos().forEach(c -> c.setPessoa(pessoa));
	
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
	
		return this.pessoaRepository.save(pessoaSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, @Valid Boolean ativo) {
		Pessoa pessoaSalva = bucarPessoaPeloCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}
	
	public Pessoa bucarPessoaPeloCodigo(Long codigo) {
		Pessoa pessoaSalva = this.pessoaRepository.findById(codigo)
	    .orElseThrow(() -> new EmptyResultDataAccessException(1));
		return pessoaSalva;
	}

	public Pessoa salvar(Pessoa pessoa) {
		pessoa.getContatos().forEach(c -> c.setPessoa(pessoa));
		return pessoaRepository.save(pessoa);
	}
}
