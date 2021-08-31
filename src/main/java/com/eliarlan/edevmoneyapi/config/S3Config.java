package com.eliarlan.edevmoneyapi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.eliarlan.edevmoneyapi.config.property.EdevmoneyApiProperty;

public class S3Config {
	
	private EdevmoneyApiProperty property;
	
	public AmazonS3 amazonS3() {
		AWSCredentials credenciais = new BasicAWSCredentials(
				property.getS3().getAccessKeyId(),
				property.getS3().getSecretAccessKey());
		
		AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credenciais))
				.build();
		
		return amazonS3;
				
	}
}
