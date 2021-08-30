package com.eliarlan.edevmoneyapi.service;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.eliarlan.edevmoneyapi.dto.LancamentoEstatisticaPessoa;
import com.eliarlan.edevmoneyapi.model.Lancamento;
import com.eliarlan.edevmoneyapi.model.Pessoa;
import com.eliarlan.edevmoneyapi.repository.LancamentoRepository;
import com.eliarlan.edevmoneyapi.repository.PessoaRepository;
import com.eliarlan.edevmoneyapi.service.exception.PessoaInexistenteOuInativaException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service
public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Scheduled(fixedDelay = 1000 * 5)
	public void avisarSobreLancamentosVencidos() {
//		System.out.println(">>>>>>>>>>>>>>>sendo executado");
	}

	public byte[] relatorioPorPessoa(LocalDate inicio, LocalDate fim) throws Exception{
		List<LancamentoEstatisticaPessoa> dados = lancamentoRepository.porPessoa(inicio, fim);
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(inicio));
		parametros.put("DT_FIM", Date.valueOf(fim));
		parametros.put("REPORT LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/relatorios/lancamentos_por_pessoa.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(dados));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
		
	}
	
	public Lancamento salvar(Lancamento lancamento) {
		validarPessoa(lancamento);

		return lancamentoRepository.save(lancamento);
	}

	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
		if (!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())) {
			validarPessoa(lancamento);
		}

		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");

		return lancamentoRepository.save(lancamentoSalvo);
	}

	private void validarPessoa(Lancamento lancamento) {
	    Optional<Pessoa> pessoaOpt = null;  

	    if (lancamento.getPessoa().getCodigo() != null) {
	        pessoaOpt = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
	    }
		
	    if (pessoaOpt == null || !pessoaOpt.isPresent() || pessoaOpt.get().isInativo()) {
	        throw new PessoaInexistenteOuInativaException();
	    }
	}

	private Lancamento buscarLancamentoExistente(Long codigo) {
	    Optional<Lancamento> lancamentoSalvoOpt = lancamentoRepository.findById(codigo);

	    // se o valor estiver presente, retorna o valor, senão lança uma exceção
	    return lancamentoSalvoOpt.orElseThrow(() -> new IllegalArgumentException()); 
	}
}