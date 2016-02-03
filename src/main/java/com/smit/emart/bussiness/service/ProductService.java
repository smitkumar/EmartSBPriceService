package com.smit.emart.bussiness.service;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;

import rx.Observable;
import rx.functions.Func1;

@Service
public class ProductService {

	private static final Logger log = Logger.getLogger( ProductService.class.getName());
	
	/**
     * Show all Product Price for a given product ID Asynchronously.
     */
    public static ResponseEntity<String> getProductPriceById(final Bucket bucket, final String productId) {
        
    	log.info("Inside getprductById method ");
    	System.out.println("message2");
    	return bucket.async()
                     .get(productId)
                     .map(new Func1<JsonDocument, ResponseEntity<String>>() {
                         public ResponseEntity<String> call(JsonDocument doc) {
                        	 System.out.println("JsonDocument doc "+doc);
                        	 System.out.println("JsonDocument doc2 "+(doc.content().toString()));
                        	// TODO Auto-generated method stub
                        	 return new ResponseEntity<String>(doc.content().toString(), HttpStatus.OK);
                        	
                        }
                     })
                     .defaultIfEmpty(new ResponseEntity<String>("{Failure: 'No Product found for given ID'}", HttpStatus.NOT_FOUND))
                     .toBlocking()
                     .single();
    	
    	
    	
    	
    }
   
}
