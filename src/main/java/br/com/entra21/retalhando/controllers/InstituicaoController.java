package br.com.entra21.retalhando.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.entra21.retalhando.models.Empresa;
import br.com.entra21.retalhando.models.Endereco;
import br.com.entra21.retalhando.models.Ong;
import br.com.entra21.retalhando.models.Responsavel;
import br.com.entra21.retalhando.repository.EmpresaRepository;
import br.com.entra21.retalhando.repository.EnderecoRepository;
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
	private OngRepository or;

	@Autowired
	private ProdutoRepository pr;

	@Autowired
	private ResponsavelRepository respr;

	@Autowired
	private RetalhoRepository retr;
	
	// CADASTRO
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String cadastrar() {
		return "instituicao/cadastro";
	}

	// CADASTRO DE EMPRESA
	@RequestMapping(value = "/cadastrar/empresa", method = RequestMethod.GET)
	public String cadastrarEmpresa() {
		return "instituicao/cadastrarEmpresa";
	}

	@RequestMapping(value = "/cadastrar/empresa", method = RequestMethod.POST)
	public String cadastrarEmpresaPost(Empresa empresa, Endereco endereco, Responsavel responsavel) {

		empresa.setSenha(new BCryptPasswordEncoder().encode(empresa.getSenha()));
		
		endr.save(endereco);
		respr.save(responsavel);

		empresa.setEndereco(endereco);
		empresa.addResponsavel(responsavel);

		empr.save(empresa);

		return "redirect:/cadastrar/empresa";
	}

	// CADASTRO DA ONG
	@RequestMapping(value = "/cadastrar/ong", method = RequestMethod.GET)
	public String cadastrarOng() {
		return "instituicao/cadastrarOng";
	}

	@RequestMapping(value = "/cadastrar/ong", method = RequestMethod.POST)
	public String cadastrarOngPost(Ong ong, Endereco endereco, Responsavel responsavel) {

		ong.setSenha(new BCryptPasswordEncoder().encode(ong.getSenha()));
		
		endr.save(endereco);
		respr.save(responsavel);

		ong.setEndereco(endereco);
		ong.addResponsavel(responsavel);
		
		or.save(ong);


		return "redirect:/cadastrar/ong";
	}

	@RequestMapping(value = "/perfil-empresa", method = RequestMethod.GET)
	public String perfilEmpresa() {
		return "instituicao/perfilEmpresa";
	}

	// OUTROS

	@RequestMapping("/cadastrar-retalho")
	public String cadastrarRetalho() {
		return "instituicao/cadastrarRetalho";
	}

	@RequestMapping("/descricao-retalho")
	public String descricaoRetalho() {
		return "instituicao/descricaoRetalho";

	}

	@RequestMapping("/buscar-instituicoes")
	public String buscarInstituicoes() {
		return "instituicao/buscarInstituicoes";
	}
}
