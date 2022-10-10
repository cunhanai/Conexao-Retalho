package br.com.entra21.conexaoretalho.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.entra21.conexaoretalho.models.Empresa;
import br.com.entra21.conexaoretalho.models.Endereco;
import br.com.entra21.conexaoretalho.models.Instituicao;
import br.com.entra21.conexaoretalho.models.Ong;
import br.com.entra21.conexaoretalho.models.Responsavel;
import br.com.entra21.conexaoretalho.models.Role;
import br.com.entra21.conexaoretalho.models.Usuario;
import br.com.entra21.conexaoretalho.repository.EmpresaRepository;
import br.com.entra21.conexaoretalho.repository.EnderecoRepository;
import br.com.entra21.conexaoretalho.repository.InstituicaoRepository;
import br.com.entra21.conexaoretalho.repository.OngRepository;
import br.com.entra21.conexaoretalho.repository.ProdutoRepository;
import br.com.entra21.conexaoretalho.repository.ResponsavelRepository;
import br.com.entra21.conexaoretalho.repository.RetalhoRepository;
import br.com.entra21.conexaoretalho.repository.RoleRepository;
import br.com.entra21.conexaoretalho.repository.UsuarioRepository;

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

	@Autowired
	private UsuarioRepository ur;

	@Autowired
	private RoleRepository rr;

	// CADASTRO

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String cadastrar() {
		return "instituicao/cadastro";
	}

	// CADASTRO DE EMPRESA
	@RequestMapping(value = "/cadastrar/empresa", method = RequestMethod.GET)
	public String cadastrarEmpresa(Model model) {
		return "instituicao/cadastrarEmpresa";
	}

	@RequestMapping(value = "/cadastrar/empresa", method = RequestMethod.POST)
	public String cadastrarEmpresaPost(@Valid Empresa empresa, Endereco endereco, Responsavel responsavel, Usuario user,
			BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Campos nulos não são permitidos!");
			return "redirect:/cadastrar/empresa";
		}
		
		Usuario usr = ur.findByLogin(user.getLogin());
		if (usr != null) {
			model.addAttribute("cnpjExiste", "CNPJ já cadastrado!");
			return "redirect:/cadastrar/empresa";
		}

		Role role = rr.findByNomeRole("ROLE_USER_EMPR");
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user.setRoles(roles);
		user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
		user.setLogin(empresa.getCnpj());
		ur.save(user);

		endereco.setInstituicao(empresa);
		responsavel.setInstituicao(empresa);

		empr.save(empresa);
		endr.save(endereco);
		respr.save(responsavel);

		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		return "redirect:/cadastrar/empresa";
	}

	// CADASTRO DA ONG
	@RequestMapping(value = "/cadastrar/ong", method = RequestMethod.GET)
	public String cadastrarOng() {
		return "instituicao/cadastrarOng";
	}

	@RequestMapping(value = "/cadastrar/ong", method = RequestMethod.POST)
	public String cadastrarOngPost(Ong ong, Endereco endereco, Responsavel responsavel, Usuario user) {

		Role role = rr.findByNomeRole("ROLE_USER_ONG");
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user.setRoles(roles);
		user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
		user.setLogin(ong.getCnpj());
		ur.save(user);

		endereco.setInstituicao(ong);
		responsavel.setInstituicao(ong);

		or.save(ong);
		endr.save(endereco);
		respr.save(responsavel);

		return "redirect:/cadastrar/ong";
	}

	@RequestMapping(value = "/buscar-instituicoes", method = RequestMethod.GET)
	public String buscarInstituicoes() {
		return "instituicao/buscarInstituicoes";
	}

	// BUSCAR INSTITUIÇÕES E PEGA A LISTA DE INSTITUIÇÕES
	@RequestMapping(value = "/listaEmpresas", method = RequestMethod.GET)
	public String listaEmpresas() {
		ModelAndView mv = new ModelAndView("instituicao/listaEmpresas");
		// PROCURA A LISTA DE INSTITUICOES
		Iterable<Instituicao> instituicoes = ir.findAll();
		mv.addObject("instituicoes", instituicoes); // instituicoes = atributo que esta no html

		return "instituicao/listaEmpresas";
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

}
