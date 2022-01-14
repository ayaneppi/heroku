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
	private UserDetailsService userDetailsService;
	
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
        		.usernameParameter("username")//Usernameのパラメータとして使用する項目のnameを設定する
    			.passwordParameter("password")//Passwordのパラメータとして使用する項目のnameを設定する
        		.defaultSuccessUrl("/index",true)
        		.failureUrl("/login-error")
        		.permitAll();
        	
        //CSRF対策を無効に設定（一時的）
        http.csrf().disable();
	    
	 }
	@Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
    //UserDetailsServiceを設定してDaoAuthenticationProviderを有効化する
     auth.userDetailsService(userDetailsService);
     //上記作成のエンコードを設定しハッシュ化する
    // passwordEncoder(passwordEncoder());
    }
	/*
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
    */
}
