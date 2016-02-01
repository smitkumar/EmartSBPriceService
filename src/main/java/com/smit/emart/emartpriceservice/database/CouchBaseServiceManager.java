package com.smit.emart.emartpriceservice.database;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.smit.emart.emartpriceservice.pojo.ProductPrice;

//@Service
public class CouchBaseServiceManager {
	
	public CouchBaseServiceManager(){
		
	}	
	
	
	public boolean addItem() {

		// Connect to localhost
		Cluster cluster = CouchbaseCluster.create();
		
		System.out.println("connected to localhost");

		// Open the default bucket and the "beer-sample" one
		Bucket defaultBucket = cluster.openBucket();
		Bucket beerSampleBucket = cluster.openBucket("beer-sample");
		
		System.out.println("beer-sample");

		JsonObject user = JsonObject.empty().put("firstname", "Walter").put("lastname", "White")
				.put("job", "chemistry teacher").put("age", 50);
		JsonDocument stored = defaultBucket.upsert(JsonDocument.create("walter", user));

		JsonDocument walter = defaultBucket.get("walter");
		System.out.println("Found: " + walter.content().getString("firstname"));

		// Disconnect and clear all allocated resources
		cluster.disconnect();

		return true;
	}
	
	
	public Map<String,String> gePricetListing(String productName){
		// Connect to localhost
				Cluster cluster = CouchbaseCluster.create();				
				System.out.println("connected to localhost");

				// Open the default bucket and the "Product-Info" one
				
				Bucket beerSampleBucket = cluster.openBucket("Product-Info");
				
		JsonDocument walter = beerSampleBucket.get(productName);
		System.out.println("Found: " + walter);
		String name=(String)walter.content().get("name");
		String price=(String)walter.content().get("price");
		
		Map<String,String> prodPrice =new HashMap<String,String>();
		prodPrice.put("product Name :", name);
		prodPrice.put("Product price :", price);
		
		return prodPrice  ;
	}
}
