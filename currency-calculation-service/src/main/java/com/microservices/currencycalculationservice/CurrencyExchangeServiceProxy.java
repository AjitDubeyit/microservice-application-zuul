package com.microservices.currencycalculationservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
 * name = name of the service - u will get it in the application.properties of that service
 * example- currency-exchange-service
 * url - is the port on which that service is running
 * feign will act as a rest client to connect to that service
 */
//@FeignClient(name="currency-exchange-service", url="localhost:8000")
@FeignClient(name="zuul-gateway")
@RibbonClient(name="currency-exchange-service") // load balancer
public interface CurrencyExchangeServiceProxy {
   
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, 
			@PathVariable("to") String to); 

   	
	@GetMapping("/name/{name}")
	public String greet(@PathVariable("name") String name);
	
}
