package br.com.entra21.conexaoretalho.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.entra21.conexaoretalho.models.AgendaColeta;
import br.com.entra21.conexaoretalho.models.Empresa;
import br.com.entra21.conexaoretalho.models.Endereco;
import br.com.entra21.conexaoretalho.models.Instituicao;
import br.com.entra21.conexaoretalho.models.Ong;
import br.com.entra21.conexaoretalho.models.Produto;
import br.com.entra21.conexaoretalho.models.Responsavel;
import br.com.entra21.conexaoretalho.models.Retalho;
import br.com.entra21.conexaoretalho.models.Role;
import br.com.entra21.conexaoretalho.models.Usuario;
import br.com.entra21.conexaoretalho.repository.AgendaColetaRepository;
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
	

	@Autowired
	private AgendaColetaRepository acr;

	// PRINCIPAl
	@RequestMapping("/principal")
	public ModelAndView principal() {

		ModelAndView mv = new ModelAndView("principal");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		Instituicao instituicao = ir.findByCnpj(username);
		mv.addObject("instituicao", instituicao);

		return mv;
	}

	// CADASTRO

	// CADASTRO DE EMPRESA
	@RequestMapping(value = "/cadastrar/empresa", method = RequestMethod.GET)
	public String cadastrarEmpresa(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "instituicao/cadastrarEmpresa";
	}

	@RequestMapping(value = "/cadastrar/empresa", method = RequestMethod.POST)
	public String cadastrarEmpresaPost(Empresa empresa, Endereco endereco, Responsavel responsavel, Usuario user,
			BindingResult result, Model model, RedirectAttributes attributes) {

		Role role = rr.findByNomeRole("ROLE_USER_EMPR");
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user.setRoles(roles);
		user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
		user.setLogin(empresa.getCnpj());

		endereco.setInstituicao(empresa);
		responsavel.setInstituicao(empresa);

		// SAVING INTO DATABASE
		ur.save(user);
		empr.save(empresa);
		endr.save(endereco);
		respr.save(responsavel);

		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		return "redirect:/login";
	}

	// CADASTRO DA ONG
	@RequestMapping(value = "/cadastrar/ong", method = RequestMethod.GET)
	public String cadastrarOng(Model model) {
		model.addAttribute("usuario", new Usuario());
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

		return "redirect:/login";
	}

	// BUSCAR INSTITUIÇÕES E PEGA A LISTA DE INSTITUIÇÕES
	// PEGA E MOSTRA A LISTA DE INSTITUIÇÕES -- GET
	@RequestMapping(value = "/listaEmpresas", method = RequestMethod.GET) // Corrigido
	public ModelAndView listaEmpresas(@RequestParam(value = "buscarnome", defaultValue = "") String buscarnome) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String usuario = auth.getName();

		String pacote = (or.findByCnpj(usuario) == null) ? "empresaView" : "ongView";
		
		ModelAndView mv = new ModelAndView(pacote + "/listaEmpresas");

		Iterable<Instituicao> instituicoes = ir.findAll();
		mv.addObject("instituicoes", instituicoes);

		mv.addObject("instituicoes", ir.findByNomeInstituicaoIgnoreCaseContaining(buscarnome));

		return mv;
	}

	// PERFIS

	// PERFIL DA EMPRESA
	@RequestMapping(value = "/{cnpj}", method = RequestMethod.GET)
	public ModelAndView perfilEmpresa(@PathVariable("cnpj") String cnpj) {
		ModelAndView mv;
		Instituicao instituicao;

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String usuario = auth.getName();

		String pacote = (cnpj.equals(usuario)) ? "userView"
				: ((or.findByCnpj(usuario) == null) ? "empresaView" : "ongView");

		if (or.findByCnpj(cnpj) != null) {

			mv = new ModelAndView(pacote + "/perfilOng");
			Ong ong = or.findByCnpj(cnpj);
			mv.addObject("instituicao", ong);

			Iterable<Produto> produtos = pr.findByOng(ong);
			mv.addObject("lprodutos", produtos);

			instituicao = ong;

		} else if (empr.findByCnpj(cnpj) != null) {
			mv = new ModelAndView(pacote + "/perfilEmpresa");
			Empresa empresa = empr.findByCnpj(cnpj);
			mv.addObject("instituicao", empresa);

			Iterable<Retalho> retalhos = retr.findByEmpresa(empresa);
			mv.addObject("lretalhos", retalhos);

			instituicao = empresa;

		} else {
			return mv = new ModelAndView("erro");
		}

		Iterable<Responsavel> responsaveis = respr.findByInstituicao(instituicao);
		mv.addObject("lresponsaveis", responsaveis);

		Endereco endereco = endr.findByInstituicao(instituicao);
		mv.addObject("endereco", endereco);

		return mv;
	}

	@RequestMapping(value = "/perfil", method = RequestMethod.GET)
	public String perfilUser() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String usuario = auth.getName();

		return "redirect:/" + usuario;
	}

	// EDITAR PERFIL
	@RequestMapping(value = "/{cnpj}/editar", method = RequestMethod.GET)
	public ModelAndView editarPerfil(@PathVariable("cnpj") String cnpj) {
		ModelAndView mv = new ModelAndView("userView/editarPerfil");
		Instituicao instituicao = ir.findByCnpj(cnpj);
		mv.addObject("instituicao", instituicao);
		return mv;
	}

	@RequestMapping(value = "/{cnpj}/editar", method = RequestMethod.POST)
	public String editarPerfilPost(@PathVariable("cnpj") String cnpj, Instituicao instituicao) {

		ir.save(instituicao);

		return "redirect:/{cnpj}";
	}

	/*
	 * 
	 * 
	 * 
	 * @RequestMapping(value = "/editar", method = RequestMethod.POST) public String
	 * editarEventoPost(long codigo, @Valid Evento evento, BindingResult result,
	 * RedirectAttributes attributes) {
	 * 
	 * // VERIFICA SE OS CAMPOS FORAM PREENCHIDOS if (result.hasErrors()) {
	 * attributes.addFlashAttribute("mensagem", "Verifique os campos!"); return
	 * "redirect:/editar"; }
	 * 
	 * er.save(evento); return "redirect:/eventos"; }
	 */

	// RETALHO

	// CADASTRAR RETALHO
	@RequestMapping(value = "/{cnpj}/retalho/cadastrar", method = RequestMethod.GET)
	public ModelAndView cadastrarRetalho(@PathVariable("cnpj") String cnpj) {
		Empresa empresa = empr.findByCnpj(cnpj);
		ModelAndView mv = new ModelAndView("retalho/cadastrarRetalho");
		mv.addObject("empresa", empresa);

		return mv;
	}

	@RequestMapping(value = "/{cnpj}/retalho/cadastrar", method = RequestMethod.POST)
	public String cadastrarRetalhoPost(@PathVariable("cnpj") String cnpj, Retalho retalho) {
		Empresa empresa = empr.findByCnpj(cnpj);
		retalho.setEmpresa(empresa);

		List<Retalho> empRetalhos = empresa.getRetalhos();
		empRetalhos.add(retalho);
		empresa.setRetalhos(empRetalhos);

		retr.save(retalho);
		empr.save(empresa);

		return "redirect:/{cnpj}";
	}

	@RequestMapping(value = "/{cnpj}/retalho/{codigo}/editar", method = RequestMethod.GET)
	public ModelAndView editarRetalho(@PathVariable("cnpj") String cnpj, @PathVariable("codigo") long codigo) {
		ModelAndView mv = new ModelAndView("retalho/editarRetalho");

		Empresa empresa = empr.findByCnpj(cnpj);
		mv.addObject("empresa", empresa);

		Retalho retalho = retr.findByCodigo(codigo);
		mv.addObject("retalho", retalho);

		return mv;
	}

	@RequestMapping(value = "/{cnpj}/retalho/{codigo}/editar", method = RequestMethod.POST)
	public String editarRetalhoPost(@PathVariable("cnpj") String cnpj, @PathVariable("codigo") long codigo,
			Retalho retalho) {
		Empresa empresa = empr.findByCnpj(cnpj);
		retalho.setEmpresa(empresa);
		retr.save(retalho);
		
		return "redirect:/{cnpj}";
	}

	@RequestMapping(value = "/{cnpj}/retalho/{codigo}/agendar", method = RequestMethod.GET)
	public ModelAndView agendarColeta(@PathVariable("cnpj") String cnpj, @PathVariable("codigo") long codigo) {
		ModelAndView mv = new ModelAndView("retalho/agendarColeta");

		Retalho retalho = retr.findByCodigo(codigo);
		Empresa empresa = empr.findByCnpj(cnpj);
		Endereco endereco = endr.findByInstituicao(empresa);

		mv.addObject("empresa", empresa);
		mv.addObject("retalho", retalho);
		mv.addObject("endereco", endereco);

		return mv;
	}

	@RequestMapping(value = "/{cnpj}/retalho/{codigo}/agendar", method = RequestMethod.POST)
	public String agendarColetaPost(@PathVariable("cnpj") String cnpj, @PathVariable("codigo") long codigo,
			AgendaColeta agendaColeta) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		Empresa empresa = empr.findByCnpj(cnpj);
		Ong ong = or.findByCnpj(username);
		Retalho retalho = retr.findByCodigo(codigo);

		agendaColeta.setEmpresa(empresa);
		agendaColeta.setOng(ong);
		agendaColeta.setRetalho(retalho);

		empresa.addColetas(agendaColeta);
		ong.addColetas(agendaColeta);
		retalho.setColeta(agendaColeta);

		acr.save(agendaColeta);

		return "redirect:/{cnpj}/retalho/{codigo}";
	}
	
	@RequestMapping("/cadastrar/produto")
	public String cadastrarProduto() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		
		return "redirect:/" + username + "/produto/cadastrar";
	}
	
	@RequestMapping("/cadastrar/retalho")
	public String cadastrarRetalho() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		

		return "redirect:/" + username + "/retalho/cadastrar";
	}
	

	@RequestMapping(value = "/{cnpj}/produto/cadastrar", method = RequestMethod.GET)
	public ModelAndView cadastrarProdutos(@PathVariable("cnpj") String cnpj) {
		Ong ong = or.findByCnpj(cnpj);
		ModelAndView mv = new ModelAndView("produtos/produtosOngs");
		mv.addObject("ong", ong);

		return mv;
	}

	@RequestMapping(value = "/{cnpj}/produto/cadastrar", method = RequestMethod.POST)
	public String cadastrarProdutosPost(@PathVariable("cnpj") String cnpj, Produto produto) {
		Ong ong = or.findByCnpj(cnpj);
		produto.setOng(ong);
		List<Produto> ongProdutos = ong.getProdutos();
		ongProdutos.add(produto);
		ong.setProdutos(ongProdutos);

		pr.save(produto);
		or.save(ong);

		return "redirect:/{cnpj}";
	}
	
	@RequestMapping(value = "/{cnpj}/produto/{id}/editar", method = RequestMethod.GET)
	public ModelAndView editarProduto(@PathVariable("cnpj") String cnpj, @PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("produtos/editarProduto");

		Ong ong = or.findByCnpj(cnpj);
		mv.addObject("ong", ong);
		
		Produto produto = pr.findById(id);
		mv.addObject("produto", produto);
		
		return mv;
	}

	@RequestMapping(value = "/{cnpj}/produto/{id}/editar", method = RequestMethod.POST)
	public String editarProdutoPost(@PathVariable("cnpj") String cnpj, @PathVariable("id") long id,
			Produto produto) {
		Ong ong = or.findByCnpj(cnpj);
		produto.setOng(ong);
		pr.save(produto);
		
		return "redirect:/{cnpj}";
	}
	
	@RequestMapping("/deletarProduto")
	public String deletarProduto(long id) {
		Produto produto = pr.findById(id);
		pr.delete(produto);
		
		Ong ong = produto.getOng();
		
		return "redirect:/" + ong.getCnpj();
	}

}
