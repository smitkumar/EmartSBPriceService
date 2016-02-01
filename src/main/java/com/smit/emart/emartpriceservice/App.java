package com.smit.emart.emartpriceservice;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

//import com.smit.springboot.Application;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 ApplicationContext ctx = SpringApplication.run(ProductPriceController.class, args);
         
      
    	 System.out.println("***************************************************************");	 
    	 
    	 System.out.println("Let's inspect the beans provided by Spring Boot:");
    	 
    	 System.out.println("***************************************************************");
         
         String[] beanNames = ctx.getBeanDefinitionNames();
         Arrays.sort(beanNames);
         for (String beanName : beanNames) {
             System.out.println(beanName);
         }
         System.out.println("******************** Server Started **********************************");
   
    }
}
