package com.securi.Secucom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

//import com.securi.Secucom.Configuration.RsakeysConfig;
import com.securi.Secucom.Models.Collaborateur;
import com.securi.Secucom.Models.MesRoles;
import com.securi.Secucom.Models.Role;
import com.securi.Secucom.Repository.RoleRepos;
import com.securi.Secucom.Service.CollaborateurService;

//@EnableConfigurationProperties(RsakeysConfig.class)
@SpringBootApplication
public class ApisecucomApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ApisecucomApplication.class, args);
		RoleRepos roleRepos = ctx.getBean(RoleRepos.class);

		CollaborateurService collaborateurService = ctx.getBean(CollaborateurService.class);

		Role R1Sawved = roleRepos.findByNom(MesRoles.USER);
		if (roleRepos.findByNom(MesRoles.USER) == null) {
			Role userRole = new Role();
			// userRole.setId(1L);
			userRole.setNom(MesRoles.USER);
			R1Sawved = roleRepos.save(userRole);

		}

		Role R2Sawved = roleRepos.findByNom(MesRoles.ADMIN);
		if (roleRepos.findByNom(MesRoles.ADMIN) == null) {
			Role adminRole = new Role();
			// adminRole.setId(2L);
			adminRole.setNom(MesRoles.ADMIN);

			R2Sawved = roleRepos.save(adminRole);
		}

		if (collaborateurService.findByPseudo("ballo") == null) {
			Collaborateur SuperAdmin = new Collaborateur();
			// SuperAdmin.setId(1L);
			SuperAdmin.setNom("Ballo");
			SuperAdmin.setPrenom("Ibrahima");
			SuperAdmin.setPseudo("ballo");
			SuperAdmin.setPassword("Bababallo");

			SuperAdmin.getRoles().add(R1Sawved);
			SuperAdmin.getRoles().add(R2Sawved);

			collaborateurService.createCollaborateur(SuperAdmin);

		}

	}

}

/**
 * <properties>
 * <java.version>1.8</java.version>
 * </properties>
 * <dependencies>
 * <dependency>
 * <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-data-jpa</artifactId>
 * </dependency>
 * <dependency>
 * <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-web</artifactId>
 * </dependency>
 * 
 * <dependency>
 * <groupId>com.mysql</groupId>
 * <artifactId>mysql-connector-j</artifactId>
 * <scope>runtime</scope>
 * </dependency>
 * <dependency>
 * <groupId>org.projectlombok</groupId>
 * <artifactId>lombok</artifactId>
 * <optional>true</optional>
 * </dependency>
 * <dependency>
 * <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-test</artifactId>
 * <scope>test</scope>
 * </dependency>
 * 
 * <!-- https://mvnrepository.com/artifact/com.auth0/java-jwt -->
 * <dependency>
 * <groupId>com.auth0</groupId>
 * <artifactId>java-jwt</artifactId>
 * <version>4.2.1</version>
 * </dependency>
 * <dependency>
 * <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-security</artifactId>
 * </dependency>
 * 
 * 
 * 
 * <dependency>
 * <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
 * </dependency>
 * 
 * <!--
 * https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-configuration-processor
 * -->
 * <dependency>
 * <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-configuration-processor</artifactId>
 * <version>2.7.5</version>
 * </dependency>
 * 
 * <dependency>
 * <groupId>org.bouncycastle</groupId>
 * <artifactId>bcpkix-jdk15on</artifactId>
 * <version>1.58</version>
 * </dependency>
 * 
 * </dependencies>
 * 
 * 
 * <properties>
 * <java.version>1.8</java.version>
 * </properties>
 * 
 **/