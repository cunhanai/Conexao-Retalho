package br.com.entra21.retalhando.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.entra21.retalhando.models.Empresa;
import br.com.entra21.retalhando.models.Endereco;
import br.com.entra21.retalhando.models.Responsavel;
import br.com.entra21.retalhando.repository.EmpresaRepository;
import br.com.entra21.retalhando.repository.EnderecoRepository;
import br.com.entra21.retalhando.repository.InstituicaoRepository;
import br.com.entra21.retalhando.repository.OngRepository;
import br.com.entra21.retalhando.repository.ProdutoRepository;
import br.com.entra21.retalhando.repository.ResponsavelRepository;
import br.com.entra21.retalhando.repository.RetalhoRepository;

@Controller
public class InstituicaoController {

	// Beans

	@Autowired
	private EmpresaRepository empr;

	@Autowired
	private EnderecoRepository endr;

	@Autowired
	private InstituicaoRepository ir;

	@Autowired
	private OngRepository or;

	@Autowired
	private ProdutoRepository pr;

	@Autowired
	private ResponsavelRepository respr;

	@Autowired
	private RetalhoRepository retr;

	// CADASTRO DE EMPRESA

	// dados basicos
	@RequestMapping(value = "/cadastrar/empresa", method = RequestMethod.GET)
	public String cadastrarEmpresaDados() {
		return "instituicao/cadastrarEmpresa";
	}

	@RequestMapping(value = "/cadastrar/empresa", method = RequestMethod.POST)
	public String cadastrarEmpresaDadosPost(Empresa empresa, Endereco endereco, Responsavel responsavel) {
		
		empr.save(empresa);
		endr.save(endereco);
		respr.save(responsavel);

		return "redirect:/index.html";
	}
	
	@RequestMapping(value = "/cadastrar/perfilEmpresa", method = RequestMethod.GET)
	public String perfilEmpresa() {
		return "instituicao/perfilEmpresa";
	}
	
	// OUTROS
	
	@RequestMapping("/cadastro/Ong")
	public String cadastrarOng() {
		return "instituicao/cadastrarOng";
	}
	@RequestMapping("/cadastro/Retalho")
	public String cadastrarRetalho() {
		return "instituicao/cadastrarRetalho";
	}
	
	
	@RequestMapping("/descricao/Retalho")
	public String descricaoRetalho() {
		return "instituicao/descricaoRetalho";
	
	}
}
