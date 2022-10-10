package br.com.entra21.conexaoretalho.secuirty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementsUserDatailsService userDatailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.httpBasic().disable()
		.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/cadastrar/ong").permitAll()
			.antMatchers(HttpMethod.GET, "/cadastrar/empresa").permitAll()
			.antMatchers(HttpMethod.GET, "/cadastrar").permitAll()
			.antMatchers(HttpMethod.POST, "/cadastrar").permitAll()
			.antMatchers(HttpMethod.POST, "/cadastrar/ong").permitAll()
			.antMatchers(HttpMethod.POST, "/cadastrar/empresa").permitAll()
			.anyRequest().authenticated()
		.and()
			.formLogin()
				.loginPage("/login").permitAll()
				// change it once the inside main page is created
				.defaultSuccessUrl("/", true)
				.failureUrl("/login?error=true")
		.and()
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
		.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
				.logoutSuccessUrl("/afterlogout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDatailsService)
		.passwordEncoder(new BCryptPasswordEncoder());

//		auth.inMemoryAuthentication()
//		.withUser("hello").password("{noop}123").roles("ADMIN");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/materialize/**", "/style/**", "/error");
	}
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
	    return new CustomAuthenticationFailureHandler();
	} 

	

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
	   return new CustomAccessDeniedHandler();
	}

}
