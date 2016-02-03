package com.smit.emart.emartpriceservice.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;

@Configuration
public class SBCauchbaseConfiguration{


    @Value("${hostname}")
    private String hostname;

    @Value("${bucket}")
    private String bucket;

    @Value("${password}")
    private String password;

    public @Bean Cluster cluster() {
        return CouchbaseCluster.create(hostname);
    }

    public @Bean Bucket bucket() {
        return cluster().openBucket(bucket, password);
    }

}
