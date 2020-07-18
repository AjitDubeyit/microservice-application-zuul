package com.microservices.currencycalculationservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrenncyCalculationController {
	
	@Autowired
	CurrencyExchangeServiceProxy proxy;

/*	@GetMapping("/currency-conversion/from/{from}/to/{to}/qty/{quantity}")
	public CurrencyConversionBean retrieveCurrencyValue(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		// Use RestTemplate API - HTTP Request and Response
		RestTemplate rest = new RestTemplate();

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversionBean> response = rest.getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);

		// retreive the object from the response entity body
		CurrencyConversionBean bean = response.getBody();
		bean.setQuantity(quantity);
		bean.setTotalCalculatedAmount(quantity.multiply(bean.getMultipleValues()));

		return bean;
	}
	*/
	
	// Call to feign proxy
	@GetMapping("/currency-conversion/from/{from}/to/{to}/qty/{quantity}")
	public CurrencyConversionBean retrieveCurrencyValue(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		CurrencyConversionBean bean  = proxy.retrieveExchangeValue(from, to);
		
		bean.setQuantity(quantity);
		bean.setTotalCalculatedAmount(quantity.multiply(bean.getMultipleValues()));

		return bean;
	}
	
	@GetMapping("/currency-conversion/{name}")
	public String displaygreet(@PathVariable String name)
	{
		return proxy.greet(name);
	}
	
}
