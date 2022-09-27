package br.com.entra21.retalhando.secuirty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementsUserDatailsService userDatailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/cadastrar/ong").permitAll()
		
		.antMatchers(HttpMethod.POST, "/cadastrar").permitAll()
		
		.antMatchers(HttpMethod.POST, "/cadastrar/empresa").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
		
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDatailsService)
		.passwordEncoder(new BCryptPasswordEncoder());

//		auth.inMemoryAuthentication()
//		.withUser("hello").password("{noop}123").roles("ADMIN");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/materialize/**", "/style/**");
	}

}
