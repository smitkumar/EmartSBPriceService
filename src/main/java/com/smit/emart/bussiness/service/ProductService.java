package com.smit.emart.bussiness.service;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.i;
import static com.couchbase.client.java.query.dsl.Expression.s;
import static com.couchbase.client.java.query.dsl.Expression.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.Statement;

import rx.functions.Func1;

@Service
public class ProductService {

	private static final Logger logger = Logger.getLogger( ProductService.class.getName());
	
	/**
     * Show all Product Price for a given product ID Asynchronously.
     */
    public static ResponseEntity<String> getProductPriceById(final Bucket bucket, final String productId) {
        
    	logger.info("Inside getprductById method ");
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
    
    
    /**
     * Find all Product price .
     */
    public static List<Map<String, Object>> findAll(final Bucket bucket, String pName) {
        Statement query = select(x("price").as("Product-Price"))
            .from(i(bucket.name()))
            .where(x("name").eq(s(pName)));
        logQuery(query.toString());
        N1qlQueryResult result = bucket.query(N1qlQuery.simple(query));
        if (!result.finalSuccess()) {
        	logger.warn("Query returned with errors: " + result.errors());
            throw new RuntimeException("Query error: " + result.errors());
        }      
        return extractResultOrThrow(result);
    }
    
    
    /**
     * Show all Product Price for a given product ID Asynchronously.
     */
    public static ResponseEntity<String> getProductPriceByName(final Bucket bucket, final String pName) {
        
    	logger.info("Inside getprductByName method ");
    	System.out.println("message2");
    	return bucket.async()
                     .get(pName)
                     .map(new Func1<JsonDocument, ResponseEntity<String>>() {
                         public ResponseEntity<String> call(JsonDocument doc) {
                        	 System.out.println("JsonDocument doc "+doc);
                        	 System.out.println("JsonDocument doc2 "+(doc.content().toString()));
                        	// TODO Auto-generated method stub
                        	 return new ResponseEntity<String>(doc.content().toString(), HttpStatus.OK);
                        	
                        }
                     })
                     .defaultIfEmpty(new ResponseEntity<String>("{Failure: 'No Product found with this name'}", HttpStatus.NOT_FOUND))
                     .toBlocking()
                     .single();
    	
    	
    }
    
    /**
     * Extract a N1Ql result or throw if there is an issue.
     */
    private static List<Map<String, Object>> extractResultOrThrow(N1qlQueryResult result) {
        if (!result.finalSuccess()) {
            logger.warn("Query returned with errors: " + result.errors());
            throw new RuntimeException("Query error: " + result.errors());
        }

        List<Map<String, Object>> content = new ArrayList<Map<String, Object>>();
        for (N1qlQueryRow row : result) {
            content.add(row.value().toMap());
        }
        return content;
    }

    
    /**
     * Helper method to log the executing query.
     */
    private static void logQuery(String query) {
        logger.info("Executing Query: {} " +query);
    }
   
}
