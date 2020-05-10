package com.siemens.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3Method {
    private AmazonS3 s3Client;

    public S3Method(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public S3Method() {
        s3Client = AmazonS3ClientBuilder.standard().build();
    }

    public void putObject(String bucketName, String time, String content) {
        s3Client.putObject(bucketName, time, content);
    }

    public String getObjectAsString(String bucketName, String time) {
        return s3Client.getObjectAsString(bucketName, time);
    }
}
