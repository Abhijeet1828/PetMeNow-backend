package com.petmenow.configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.pusher.rest.Pusher;

@SpringBootConfiguration
@EnableJpaRepositories(basePackages = { "com.petmenow.repository" })
@EnableTransactionManagement
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

	@Bean(name = "asyncThreadPoolExecutor")
	public Executor asyncThreadPoolExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(10);
		executor.setThreadNamePrefix("asyncThreadPoolExecutor-");
		executor.setKeepAliveSeconds(60);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}

}
