package com.smit.emart.emartpriceservice;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.couchbase.client.java.Bucket;
import com.smit.emart.bussiness.service.ProductService;



@RestController
@RequestMapping("/api/v1/product")
@EnableAutoConfiguration
public class ProductPriceController {
	
	private static final Logger log = Logger.getLogger( ProductPriceController.class.getName());
	
	private final Bucket bucket;

    @Autowired
    public ProductPriceController(Bucket bucket) {
        this.bucket = bucket;
    }
	
	
	
	@RequestMapping(value="/priceList/{itemID}", method=RequestMethod.GET)

    public Object fetchProductPrice(@PathVariable("itemID") String itemID) {
		log.info("Request URI reached to fetchProductPrice");
		System.out.println("itemID");
		return ProductService.getProductPriceById(bucket, itemID);
    }
	
	@RequestMapping(value="/price/{name}", method=RequestMethod.GET)
    public Object fetchProductPriceByName(@PathVariable("name") String name) {
		log.info("Request URI reached to fetchProductPrice");
		System.out.println("product name :"+name);
		return ProductService.getProductPriceByName(bucket, name);
    }
	
	@RequestMapping(value="/pricebyname/{name}", method=RequestMethod.GET)
    public Object fetchAllProductPrice(@PathVariable("name") String name) {
		log.info("Request URI reached to fetchProductPrice");
		System.out.println("product name :"+name);
		return ProductService.findAll(bucket, name);
    }
	
	
	/*@RequestMapping("/priceList/{itemPrice}")
     Map getProdPriceList(@PathVariable("itemPrice") String itemPrice){
		Map<String,String> priceList =new HashMap<String,String>();
		CouchBaseServiceManager couchDataManagerService=new CouchBaseServiceManager();
		try{
			 priceList=couchDataManagerService.gePricetListing(itemPrice);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}	
				
		//List priceList=JsonConvertor.gePricetListing();
		
		return priceList;
	}
	*/
	/*@RequestMapping("/addProduct")
      String getProd(){
		boolean result =false;
		String mesage=null;
		CouchBaseServiceManager couchDataManagerService=new CouchBaseServiceManager();
		try{
			 result=couchDataManagerService.addItem();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}	
				
		if(result){
			mesage="succefully updated table";
		}else{
			mesage="Creattion failed";
		}
		//List priceList=JsonConvertor.gePricetListing();
		
		return mesage;
	}*/
	
	
	
	
}



