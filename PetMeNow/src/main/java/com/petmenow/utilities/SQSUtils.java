package com.petmenow.utilities;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

@Service
public class SQSUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(SQSUtils.class);

	private AmazonSQS amazonSQS;

	@Value("${amazonProperties.sqs.url}")
	private String queueUrl;

	@Value("${cloud.aws.credentials.access-key}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secret-key}")
	private String secretKey;

	@PostConstruct
	private void initializeAmazonSQS() {
		BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
		amazonSQS = AmazonSQSClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).withRegion(Regions.US_WEST_2)
				.build();
	}

	public boolean sendMessage(String queueName, String message) {
		try {
			SendMessageRequest sendMessageRequest = new SendMessageRequest().withQueueUrl(queueUrl + queueName)
					.withMessageBody(message);
			SendMessageResult sendMessageResult = amazonSQS.sendMessage(sendMessageRequest);
			if (sendMessageResult.getMessageId() != null) {
				LOGGER.info("Posted Message to SQS, messageId {} and request {}", sendMessageResult.getMessageId(),
						message);
				return true;
			} else {
				LOGGER.error("Error posting message to SQS");
				return false;
			}
		} catch (Exception e) {
			LOGGER.error("Exception in sendMessage", e);
			return false;
		}
	}

	public AmazonSQS getAmazonSQS() {
		return amazonSQS;
	}

}
