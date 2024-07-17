package com.devsuperior.dscatalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//classe de configuração que será responsvel para criar componentes, etc

@Configuration
public class AppConfig {

    @Bean
   public BCryptPasswordEncoder passwordEncoder() {
    	//metodo para criptografar a senha. será usado no service
		return new BCryptPasswordEncoder();
	}

}
