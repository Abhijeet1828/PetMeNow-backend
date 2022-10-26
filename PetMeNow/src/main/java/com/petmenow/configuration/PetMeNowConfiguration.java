package com.petmenow.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.pusher.rest.Pusher;

@SpringBootConfiguration
public class PetMeNowConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return builder;
	}

	@Bean
	public Pusher pusher() {
		Pusher pusher = new Pusher(env.getProperty("pusher.appId"), env.getProperty("pusher.apiKey"),
				env.getProperty("pusher.secret"));
		pusher.setCluster(env.getProperty("pusher.cluster"));
		pusher.setEncrypted(true);
		return pusher;
	}
	
}
