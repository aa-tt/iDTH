package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import daos.CustomerDAO;
import entities.Customer;
import io.swagger.annotations.ApiOperation;

@RestController
public class CustomerController {
	
	@Autowired CustomerDAO customerDAO;
	
	@ApiOperation(value = "Find customer by Id", notes = "Get customer by specifying Id", httpMethod = "GET", response = Customer.class)
	//@RequestMapping(value="/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/customer/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer getCustomer(@PathVariable Integer customerId) {
		return customerDAO.getCustomerById(customerId);
	}
}
