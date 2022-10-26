package com.petmenow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = { 
		ContextInstanceDataAutoConfiguration.class, 
		ContextStackAutoConfiguration.class,
		ContextRegionProviderAutoConfiguration.class })
@EnableAsync
public class PetMeNowApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetMeNowApplication.class, args);
	}

}
