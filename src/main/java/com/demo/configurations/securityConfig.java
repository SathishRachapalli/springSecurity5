package com.demo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity(debug=true)  // debug=true to see what are all the available security filters available.

//By default the above @EnableWebSecurity enables the security to all the end points.
//we can customize it.
public class securityConfig {

	
	/// settingup/creating the users
	
	private UserDetails userDetails;


				// static way of creating users or user details
//	@Bean 
//	public InMemoryUserDetailsManager setupUsers()
//	{
//			
//		UserDetails root = User.withUsername("root")
//		.password("root")
//		.roles("admin","user")
//		.build();
//		
//		UserDetails sathish= User.withUsername("sathish")
//		.password("sathish")
//		.roles("admin","user")
//		.build();
//		
//		//InMemoryUserDetailsManager class has another constructor,
//		//that takes the user objects as arguments, so we are making use of it.
//		
//		return new InMemoryUserDetailsManager(root,sathish);
//	}
//	
	
//	@Bean
//	public  InMemoryUserDetailsManager setupUser()
//	{
//		
//		GrantedAuthority obj1=new SimpleGrantedAuthority("admin");
//		GrantedAuthority obj2=new SimpleGrantedAuthority("user");
//		
//		List<GrantedAuthority> ArrayListOne=Arrays.asList(obj1,obj2);
//		
//		
//		UserDetails userDetailsObj=new User("root", "root",ArrayListOne);
//		
//		InMemoryUserDetailsManager userDB = new InMemoryUserDetailsManager();
//		
//		userDB.createUser(userDetailsObj);
//		
//		return userDB;
//
//		
//	}
//	
//	
	@Autowired
	HttpSecurity httpsecurity;
	
	@Bean
	public PasswordEncoder getPasswordEncryptor()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	//  --------------------------------------------setting up the httpSecurity concepts-----------------
	
	//creating our own security filter here.
	// currently this would have the default security filter , means endpoints will not be secured at all.
	
	@Bean   // the httpsecurity.build() returns the default security filter here.
	public SecurityFilterChain applyingDefaultSecurity() throws Exception
	{
		// to provide/include the specific security
//		httpsecurity.httpBasic();
//		httpsecurity.formLogin();
		
		
		//to secure all the endpoints
		
		//httpsecurity.authorizeHttpRequests().anyRequest().authenticated();
		
		//------------------------------------------------------------------------
		
		//Applying different security levels to each endpoints
		
	//	httpsecurity.authorizeHttpRequests().requestMatchers("/hi","/hii").authenticated();
//		httpsecurity.authorizeHttpRequests().requestMatchers("/bye").denyAll();
//		httpsecurity.authorizeHttpRequests().requestMatchers("/hello").authenticated();
		
	//	httpsecurity.formLogin();
	//	httpsecurity.httpBasic();
		
		// NOTE: all the above methods are now deprecated due to the version conflict
		
		//note: if you are trying to access the endpoints from postman tool,
		// then we have to include 'httpsecurity.httpBasic()' also here.
		
		
		
		// from spring security version: 6.0+, we need to use customizer
		
		httpsecurity.authorizeHttpRequests(customizer->{
			
			customizer.requestMatchers("/hi","/register","/WEB-INF/views/**","/ProcessRegistration").permitAll().
			anyRequest().authenticated();
		});
		
		httpsecurity.formLogin(Customizer.withDefaults());
		httpsecurity.httpBasic(Customizer.withDefaults());		
		httpsecurity.csrf(AbstractHttpConfigurer::disable);
	
		return  httpsecurity.build();
		
		// Likewise, we can activate the other available security filters like 'basic authentication'. 
		//using httpsecurity.httpBasic();
		// and to add the default login page ->httpSecurity.formLogin();  etc,
	}
	
	
	@Bean(name="mvcHandlerMappingIntrospector")
	public HandlerMappingIntrospector mappingIntrospectorBean()
	{
		return new HandlerMappingIntrospector();
	}
	
	
}
