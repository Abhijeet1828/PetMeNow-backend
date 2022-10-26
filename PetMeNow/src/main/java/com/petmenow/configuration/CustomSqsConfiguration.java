package com.petmenow.configuration;

import java.util.Collections;

import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class CustomSqsConfiguration {

	@Bean
	public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
		return new QueueMessagingTemplate(amazonSQSAsync);
	}

	@Bean
	public QueueMessageHandlerFactory queueMessageHandlerFactory(final ObjectMapper mapper,
			final AmazonSQSAsync amazonSQSAsync) {

		final QueueMessageHandlerFactory queueHandlerFactory = new QueueMessageHandlerFactory();
		queueHandlerFactory.setAmazonSqs(amazonSQSAsync);
		queueHandlerFactory.setArgumentResolvers(
				Collections.singletonList(new PayloadMethodArgumentResolver(jackson2MessageConverter(mapper))));
		return queueHandlerFactory;
	}
	
	@Bean
	public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSqs) {
		SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
		factory.setWaitTimeOut(5);
		return factory;
	}

	private MessageConverter jackson2MessageConverter(final ObjectMapper mapper) {

		final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setObjectMapper(mapper);
		return converter;
	}

}
