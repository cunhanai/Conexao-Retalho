package br.com.entra21.conexaoretalho.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.entra21.conexaoretalho.models.Role;
import br.com.entra21.conexaoretalho.repository.RoleRepository;

@Component
public class CarregadoraDados implements CommandLineRunner {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		String[] roles = {"ROLE_ADMIN", "ROLE_USER_EMPR", "ROLE_USER_ONG"};
		
		for (String rolesString : roles) {
			Role role = roleRepository.findByNomeRole(rolesString);
			if (role == null) {
				role = new Role(rolesString);
				roleRepository.save(role);
			}
		}
		
	}

}
