package com.eliarlan.edevmoneyapi.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.eliarlan.edevmoneyapi.config.property.EdevmoneyApiProperty;
import com.eliarlan.edevmoneyapi.model.Lancamento;
import com.eliarlan.edevmoneyapi.model.Usuario;

@Component
public class Mailer {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
	@Autowired
	private EdevmoneyApiProperty prop;
	
	public void avisarSobreLancamentosVencidos(
			List<Lancamento> vencidos, List<Usuario> destinatarios) {
		Map<String, Object> variaveis = new HashMap<>();
		variaveis.put("lancamentos", vencidos);
		
		List<String> emails = destinatarios.stream()
				.map(u -> u.getEmail())
				.collect(Collectors.toList());
		
		this.enviarEmail(
				prop.getRemetente(),
				emails,
				"Lançamentos vencidos",
				"mail/aviso_lancamentos_vencidos",
				variaveis);
	}

	public void enviarEmail(String remetente,
			List<String> destinatarios, String assunto, String template, 
			Map<String, Object> variaveis) {
		
		Context context = new Context(new Locale("pt", "BR"));
		
		variaveis.entrySet().forEach(
				e -> context.setVariable(e.getKey(), e.getValue()));
		String mensagem = thymeleaf.process(template, context);
		
		this.enviarEmail(remetente, destinatarios, assunto, mensagem);
		
	}	
	
	public void enviarEmail(String remetente,
			List<String> destinatarios, String assunto, String mensagem) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			
			helper.setFrom(remetente);
			helper.setTo(destinatarios.toArray(new String[destinatarios.size()]));
			helper.setSubject(assunto);
			helper.setText(mensagem, true);
			
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException("Problemas com o envio de e-mail!!", e);
		}
		
	}
}

//@Autowired
//private LancamentoRepository repo;
//@EventListener
//public void teste(ApplicationReadyEvent event) {
//	String template = "mail/aviso_lancamentos_vencidos";
//	
//	List<Lancamento> lista = repo.findAll();
//	
//	Map<String, Object> variaveis = new HashMap<>();
//	variaveis.put("lancamentos", lista);
//	
//	
//	this.enviarEmail("remetente",
//		Arrays.asList("destinatario"),
//		"Testando", template, variaveis);
//	System.out.println("Envio Finalizado...");
//}
