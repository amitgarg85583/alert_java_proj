package com.test.alert.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.alert.dao.Customer;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class CustomerService {

	@Autowired
	private MeterRegistry meterRegistry;
	Counter foundCounter;
	Counter notfoundCounter;

    public CustomerService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        initOrderCounters();
    }
    
    private void initOrderCounters() {
        foundCounter = Counter.builder("found")
                .description("The number of found hits")
                .register(meterRegistry);
        
        notfoundCounter = Counter.builder("notfound")
                .description("The number of not found hits")
                .register(meterRegistry);
    }
    
	public Customer getCustomer(String name) {
		List<String> names =new ArrayList<String>();
		names.add("one");
		names.add("two");
		names.add("three");
		
		boolean found = names.contains(name.toLowerCase()); 
		if (found) {
			foundCounter.increment();
			System.out.println("Customer found !!");
			return new Customer(name, "BLR");
		}
		else {
			notfoundCounter.increment();
			System.out.println("Customer not found !!");
			return new Customer();
		}
	}
}
