package com.jsw.auditSystem;

import com.jsw.auditSystem.model.UserInfoAudit;
import com.jsw.auditSystem.repository.UserInfoMongoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = UserInfoMongoRepository.class)
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


	/*@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application-version}") String appVersion) {
		return new OpenAPI()
				.info(new Info()
								.title("sample application API")
								.version(appVersion)
								.description(appDesciption)
								.termsOfService("http://swagger.io/terms/")
								.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}*/
}
