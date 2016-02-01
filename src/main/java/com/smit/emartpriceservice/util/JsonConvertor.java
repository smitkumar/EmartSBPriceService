package com.smit.emartpriceservice.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smit.emart.emartpriceservice.pojo.ProductPrice;

public class JsonConvertor {
	

	// mock data to be used before caushbase
	
	public static List<ProductPrice> gePricetListing() {

	    List<ProductPrice> prices = new ArrayList<ProductPrice>();
	    prices.add(new ProductPrice("Cold Drink", 120, 1));
	    prices.add(new ProductPrice("Oil", 130, 2));
	    prices.add(new ProductPrice("SOAP", 140, 3));
	    prices.add(new ProductPrice("PULSE", 150, 4));

	    return prices;
	}
	
	
	// mock data to be used before caushbase
	
	
	public static int gePricetListing(String pName) {

	   int price=0;
	   
	   Map cache=new HashMap();
		
		cache.put("A01", "200");
		cache.put("A02", "400");
		cache.put("A03", "600");
		cache.put("A04", "800");

		price=Integer.parseInt((String)cache.get(pName));
	    return price;
	}
	
	
		
		
				

	
	
}
