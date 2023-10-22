package com.solitera.test_estimation.usermgmt.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.solitera.test_estimation.usermgmt.security.jwt.AuthTokenFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	  @Autowired
	  private UserDetailsService userDetailsService ;
	  
	  @Bean
	  public AuthTokenFilter authenticationJwtTokenFilter() {
	    return new AuthTokenFilter();
	  }
	  
	  @Bean
	  public AuthenticationEntryPoint authenticationEntryPoint() {
	        return new AuthenticationEntryPoint() {
				@Override
				public void commence(HttpServletRequest request, HttpServletResponse response,
						AuthenticationException authException) throws IOException, ServletException {
				 	String errorMessage = "User Not Authenticated";
		            String json = String.format("{\"message\": \"%s\"}", errorMessage);
		            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		            response.setContentType("application/json");
		            PrintWriter out = response.getWriter();
		            out.print(json);
		            out.flush();
				}
	        };
	    }
	  
	  @Override
	  public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	  }
	    
	    @Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

	    @Bean
	    public  BCryptPasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
	    
	    public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/user/**")
                    .allowedOrigins("*")
                    .allowedMethods("POST","GET","PUT","DELETE")
                    .allowedHeaders("content-type","Accept","Origin","X-Requested-With");
        }
	    
	    
	    @Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable()
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers("/v3/api-docs").permitAll()
				.antMatchers("/swagger-ui/*").permitAll()
				.antMatchers("/swagger-resources").permitAll()
				.antMatchers("/swagger-resources/configuration/ui").permitAll()
				.antMatchers("/swagger-resources/configuration/security").permitAll()
				.antMatchers(HttpMethod.GET, "/user/").permitAll()
				.antMatchers(HttpMethod.POST,"/user/register").permitAll()
				.antMatchers(HttpMethod.GET, "/user/country").permitAll()
				.antMatchers(HttpMethod.POST,"/user/register/verify").permitAll()
				.antMatchers(HttpMethod.POST,"/user/resendOtp").permitAll()
				.antMatchers(HttpMethod.POST,"/user/forgotPassword").permitAll()
				.antMatchers(HttpMethod.PUT,"/user/resetPassword").permitAll()
				.antMatchers(HttpMethod.GET,"/test_estimation/").permitAll()
				.antMatchers(HttpMethod.POST,"/user/authenticate").permitAll()
//				.antMatchers(HttpMethod.GET,"/user/userProfile").permitAll()
//				.antMatchers(HttpMethod.PUT,"/user/updateProfile").permitAll()
//				.antMatchers(HttpMethod.PUT,"/user/updatePassword").permitAll()
//				.antMatchers(HttpMethod.POST,"/test_estimation/createEstimation").permitAll()
//				.antMatchers(HttpMethod.DELETE,"/test_estimation/estimation/{estimationName}").permitAll()
//				.antMatchers(HttpMethod.PUT,"/test_estimation/updateEstimationName").permitAll()
//				.antMatchers(HttpMethod.POST,"/test_estimation/ideaPhase/{estimationId}").permitAll()
//				.antMatchers(HttpMethod.POST,"/test_estimation/developmentPhase/{estimationId}").permitAll()
//				.antMatchers(HttpMethod.POST,"/test_estimation/inceptionPhase/{estimationId}").permitAll()
				.anyRequest().authenticated();
			http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		}	    	    
}
