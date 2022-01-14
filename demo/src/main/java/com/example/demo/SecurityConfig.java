package com.example.demo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;

//securityのためのクラスです
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
    public void configure(WebSecurity web) throws Exception{
		//静的リソースへのアクセスには、セキュリティを適用しない
        web.ignoring().antMatchers("/webjars/∗∗", "/css/∗∗");
	}
	@Override
    protected void configure(HttpSecurity http) throws Exception {

        // ログイン不要ページの設定
        http
            .authorizeRequests()
            	.antMatchers("/").permitAll() //ホームへアクセス許可
                .antMatchers("/css/**").permitAll() //cssへアクセス許可
                .antMatchers("/login").permitAll() //ログインページは直リンクOK
                .anyRequest().authenticated(); //それ以外は直リンク禁止

        //CSRF対策を無効に設定（一時的）
        http.csrf().disable();
	    
	 }
	 @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // パスワード
        String password = passwordEncoder().encode("password");

        // インメモリの認証を行うための設定
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user").password(password).roles("USER");
    }
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
