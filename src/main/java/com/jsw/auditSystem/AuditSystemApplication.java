package com.jsw.auditSystem;

import com.jsw.auditSystem.model.UserInfoAudit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AuditSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditSystemApplication.class, args);
	}

	//creating a bean for userInfoAudit
	@Bean
	public UserInfoAudit getUserInfoAuditBean(){
		return new UserInfoAudit();
	}
}
