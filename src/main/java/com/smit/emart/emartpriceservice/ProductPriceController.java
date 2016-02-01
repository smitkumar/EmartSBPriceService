package com.smit.emart.emartpriceservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smit.emart.emartpriceservice.database.CouchBaseServiceManager;
import com.smit.emart.emartpriceservice.pojo.ProductPrice;
import com.smit.emartpriceservice.util.JsonConvertor;

@RestController
@RequestMapping("/prices")
@EnableAutoConfiguration
public class ProductPriceController {
	
	
	/*@Autowired
	public CouchBaseServiceManager couchDataManagerService;
*/
	
	@RequestMapping("/")
	String sayHello(){
		
		return "hello Spring Boot !!!!!";
	}	
	
	@RequestMapping("/list")
	List<ProductPrice> getPriceList(){
		
		List priceList=JsonConvertor.gePricetListing();
		return priceList;
	}
	
	
	@RequestMapping("/pName")
	int getPriceById(){
		
		int priceList=JsonConvertor.gePricetListing("A03");
		return priceList;
	}
	
	
	@RequestMapping("/priceList/{itemPrice}")
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
	
	@RequestMapping("/addProduct")
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
	}
	
	
	
	
}



