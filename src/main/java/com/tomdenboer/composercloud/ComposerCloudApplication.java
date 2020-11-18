package com.tomdenboer.composercloud;

import com.tomdenboer.composercloud.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

//TODO: ??????
//@EnableJpaRepositories(basePackageClasses = UserRepository.class)

public class ComposerCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComposerCloudApplication.class, args);
	}

}
