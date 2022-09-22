package br.com.entra21.retalhando.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.entra21.retalhando.models.Empresa;
import br.com.entra21.retalhando.models.Instituicao;
import br.com.entra21.retalhando.repository.EmpresaRepository;
import br.com.entra21.retalhando.repository.InstituicaoRepository;

@Controller
public class InstituicaoController {
	
	// Beans
	
	@Autowired
	private InstituicaoRepository ir;

	@Autowired
	private EmpresaRepository er;
	


	
	// CADASTRO DE EMPRESA
	
	@RequestMapping(value = "/cadastro/empresa", method = RequestMethod.GET)
	public String cadastrarEmpresa() {
		return "instituicao/cadastrarEmpresa";
	}
	
	@RequestMapping(value = "/cadastro/empresa", method = RequestMethod.POST)
	public String cadastrarEmpresaPost(Instituicao instituicao) {
		ir.save(instituicao);
		
		
		return "redirect:/cadastro/empresa";
	}
	
	// CADASTRO ONG
	@RequestMapping("/cadastro/Ong")
	public String cadastrarOng() {
		return "instituicao/cadastrarOng";
	}
}
