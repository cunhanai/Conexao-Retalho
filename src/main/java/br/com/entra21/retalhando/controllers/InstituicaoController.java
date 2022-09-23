package br.com.entra21.retalhando.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.entra21.retalhando.models.Empresa;
import br.com.entra21.retalhando.models.Endereco;
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
	@RequestMapping(value = "/cadastrar/empresa/dados-basicos", method = RequestMethod.GET)
	public String cadastrarEmpresaDados() {
		return "instituicao/cadastrarEmpresaDados";
	}

	@RequestMapping(value = "/cadastrar/empresa/dados-basicos", method = RequestMethod.POST)
	public String cadastrarEmpresaDadosPost(Empresa empresa) {
		
		empr.save(empresa);

		return "redirect:/cadastrar/empresa/endereco";
	}

	// endereco
	@RequestMapping(value = "/cadastrar/empresa/endereco", method = RequestMethod.GET)
	public String cadastrarEmpresaEndereco() {
		
		return "instituicao/cadastrarEmpresaEndereco";
	}
	
	@RequestMapping(value = "/cadastrar/empresa/endereco", method = RequestMethod.POST)
	public String cadastrarEmpresaEnderecoPost(Endereco endereco) {
		endr.save(endereco);
		
		return "redirect:/cadastrar/empresa/responsavel";
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
	@RequestMapping("/perfil/Empresa")
	public String perfilEmpresa() {
		return "instituicao/perfilEmpresa";
	
	}
	@RequestMapping("/descricao/Retalho")
	public String descricaoRetalho() {
		return "instituicao/descricaoRetalho";
	
	}
}
