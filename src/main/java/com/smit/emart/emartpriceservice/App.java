package com.smit.emart.emartpriceservice;


import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//import com.smit.springboot.Application;

/**
 * SpringBoot Application
 *
 */

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {

    	// ApplicationContext ctx = SpringApplication.run(App.class, args);
    	SpringApplication.run(App.class, args);
         
      
    	/* System.out.println("***************************************************************");	 
    	 
    	 System.out.println("Let's inspect the beans provided by Spring Boot:");
    	 
    	 System.out.println("***************************************************************");
         
         String[] beanNames = ctx.getBeanDefinitionNames();
         Arrays.sort(beanNames);
         for (String beanName : beanNames) {
             System.out.println(beanName);
         }
         System.out.println("******************** Server Started **********************************");
   
*/
        System.out.println( "Hello World!" );

    }
}
