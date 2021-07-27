package cl.christian.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cl.christian.demo.util.LoginSuccessMessage;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder passEnconder;
	
	@Autowired
	private LoginSuccessMessage successMessage;
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/prueba","/guardarPostulacion/**","/saveUsuario","/create","/cartel","/eliminarcampania/**","/index","/listarPublicidad", "/listacarteles", "/detalle/**" ,"detallecartel/**", "/nombre/detalle/**","/listacarteles","/detallecartel/**","/recursos/**","/registro","/static/**").permitAll()

		.anyRequest().authenticated()
		.and()
		.formLogin()
		.successHandler(successMessage)
		.loginPage("/login")
		.permitAll()
		.and()
		.logout().permitAll();
	}




	@Autowired
	public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passEnconder)
		.usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
		.authoritiesByUsernameQuery("SELECT u.username, r.rol FROM roles r INNER JOIN users u ON r.user_id=u.idusuario WHERE u.username=?");
		
	}
}
