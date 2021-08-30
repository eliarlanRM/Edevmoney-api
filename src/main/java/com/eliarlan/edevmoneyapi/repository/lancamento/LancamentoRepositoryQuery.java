package com.eliarlan.edevmoneyapi.repository.lancamento;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eliarlan.edevmoneyapi.dto.LancamentoEstatisticaCategoria;
import com.eliarlan.edevmoneyapi.dto.LancamentoEstatisticaDia;
import com.eliarlan.edevmoneyapi.dto.LancamentoEstatisticaPessoa;
import com.eliarlan.edevmoneyapi.model.Lancamento;
import com.eliarlan.edevmoneyapi.repository.filter.LancamentoFilter;
import com.eliarlan.edevmoneyapi.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {
	
	public List<LancamentoEstatisticaPessoa> porPessoa(LocalDate inicio, LocalDate fim);
	
	public List<LancamentoEstatisticaCategoria> porCategoria(LocalDate mesReferencia);
	public List<LancamentoEstatisticaDia> porDia(LocalDate mesReferencia);

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
	
}