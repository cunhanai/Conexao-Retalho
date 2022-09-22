package br.com.entra21.retalhando.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InstituicaoController {

	// CADASTRO DE EMPRESA
	
	@RequestMapping("/cadastro/empresa")
	public String cadastrarEmpresa() {
		return "instituicao/cadastrarEmpresa";
	}
	
	@RequestMapping("/cadastro/Ong")
	public String cadastrarOng() {
		return "instituicao/cadastrarOng";
	}
}
