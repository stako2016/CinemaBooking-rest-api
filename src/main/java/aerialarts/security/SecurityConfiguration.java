package aerialarts.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import aerialarts.model.CinemaUser;
import aerialarts.model.Role;
import aerialarts.repository.CinemaUserRepository;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	public SecurityConfiguration() {
	}

	@Autowired
	CinemaUserRepository ur;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		/*
		 * Adding default Admin/Non-Admin roles and users
		 */
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");

		List<CinemaUser> users = ur.findAll();

		for (CinemaUser user : users) {
			
			if(user.getRole().getId() == Role.ROLE_ADMIN)
				auth.inMemoryAuthentication().withUser(user.getName()).password(user.getPassword()).roles("ADMIN");
			else if(user.getRole().getId()  == Role.ROLE_USER)
				auth.inMemoryAuthentication().withUser(user.getName()).password(user.getPassword()).roles("USER");
		}

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and().authorizeRequests()

		.antMatchers(HttpMethod.GET, "/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/movies").hasRole("ADMIN") 		// Adding movies
				
		.antMatchers(HttpMethod.GET, "/cinemaUsers").hasRole("ADMIN")	// View users
		.antMatchers(HttpMethod.GET, "/roles").hasRole("ADMIN")			// View roles
			
		.antMatchers(HttpMethod.DELETE, "/cinemaShows/").hasRole("ADMIN")	//Deleting shows
		
		.antMatchers(HttpMethod.GET, "/cinemaShows").hasAnyRole("USER", "ADMIN") //View shows
		.antMatchers(HttpMethod.GET, "/movies").hasAnyRole("USER", "ADMIN")		//View movies
		.antMatchers(HttpMethod.GET, "/bookings").hasRole("ADMIN")	//View all bookings
		.antMatchers(HttpMethod.POST, "/bookings").hasAnyRole("ADMIN", "USER") //booking a show
		.antMatchers(HttpMethod.GET, "/bookings/*").hasAnyRole("USER", "ADMIN")	//View all bookings
				
		.and().csrf().disable();
	}
}
