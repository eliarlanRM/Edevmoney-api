package com.eliarlan.edevmoneyapi.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.util.StringUtils;

import com.eliarlan.edevmoneyapi.EdevmoneyApiApplication;
import com.eliarlan.edevmoneyapi.model.Lancamento;
import com.eliarlan.edevmoneyapi.storage.S3;

public class LancamentoAnexoListener {
		
		@PostLoad
		public void postLoad(Lancamento lancamento) {
			if(StringUtils.hasText(lancamento.getAnexo())) {
				S3 s3 = EdevmoneyApiApplication.getBean(S3.class);
				lancamento.setUrlAnexo(s3.configurarUrl(lancamento.getAnexo()));
			}
		}
}
