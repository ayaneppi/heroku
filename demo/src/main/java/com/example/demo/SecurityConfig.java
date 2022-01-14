package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;

//securityのためのクラスです
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetaialsService userDetailsService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {

        // ログイン不要ページの設定
        http
            .authorizeRequests()
            	//.antMatchers("/").permitAll() //ホームへアクセス許可
                .antMatchers("/css/**").permitAll() //cssへアクセス許可
                //.antMatchers("/login").permitAll() //ログインページは直リンクOK
                .anyRequest()
                .authenticated()//それ以外は直リンク禁止
                .and()
        	.formLogin()
        		.loginPage("/login")
        		.defaultSuccessUrl("/index")
        		.failureUrl("/login")
        		.permitAll();
        	
        //CSRF対策を無効に設定（一時的）
        http.csrf().disable();
	    
	 }
	 @Autowired
     void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {

        auth
         .userDetailsService(userDetailsService)
         .passwordEncoder(passwordEncoder());
    }
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
