package com.petmenow.utilities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Utils {

	private static final Logger LOGGER = LoggerFactory.getLogger(S3Utils.class);

	private AmazonS3 s3client;

	@Value("${amazonProperties.endpointUrl}")
	private String endpointUrl;

	@Value("${amazonProperties.bucketName}")
	private String bucketName;

	@Value("${cloud.aws.credentials.access-key}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secret-key}")
	private String secretKey;

	@PostConstruct
	private void initializeAmazonS3() {
		BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
		s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).withRegion(Regions.US_WEST_2)
				.build();
	}

	public String uploadFileUsingInputStream(String keyName, InputStream inputFile) {
		try {
			keyName = keyName.replaceAll("[^a-zA-Z0-9/,._-]+", "");
			
			Tika tika = new Tika();
			ObjectMetadata objectMetadata = new ObjectMetadata();
			byte[] bytes = IOUtils.toByteArray(inputFile);
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
			if (byteArrayInputStream.markSupported()) {
				byteArrayInputStream.mark(0);
			}
			objectMetadata.setContentLength(bytes.length);
			objectMetadata.setContentType(tika.detect(inputFile));
			byteArrayInputStream.reset();
			
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, keyName, byteArrayInputStream,
					objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead);
			s3client.putObject(putObjectRequest);

			return "https://" + bucketName + endpointUrl + keyName;
		} catch (Exception e) {
			LOGGER.error("Exception in uploadFileUsingInputStream", e);
			return StringUtils.EMPTY;
		}
	}
}
