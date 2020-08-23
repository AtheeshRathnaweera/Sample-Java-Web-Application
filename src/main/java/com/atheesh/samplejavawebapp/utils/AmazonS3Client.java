package com.atheesh.samplejavawebapp.utils;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AmazonS3Client {

    private static AmazonS3 s3client;

    private AmazonS3Client(){
    }

    private static void createClient(){

        // creating the client for access the S3
        String secretKey = (String) AppProperties.getProperty("amazon.s3.secretKey");
        String accessKey = (String) AppProperties.getProperty("amazon.s3.accessKey");
        String regionString = (String) AppProperties.getProperty("amazon.s3.region");

        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        s3client = AmazonS3ClientBuilder.standard().withCredentials(
                new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.fromName(regionString))
                .build();
    }

    public static AmazonS3 getS3client(){
        if(s3client == null){
            System.out.println("s3 client not found.New client created.");
            createClient();
        }
        return s3client;
    }
}
