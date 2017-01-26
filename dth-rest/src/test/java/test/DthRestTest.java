package test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import rest.CustomerController;
import webconfig.AppWebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppWebConfig.class })
@WebAppConfiguration
public class DthRestTest {
	
	private MockMvc mockMvc;
	
	AnnotationMethodHandlerAdapter handlerAdapter; // deprecated
    RequestMappingHandlerAdapter requestAdapter;   // this can be used in place of AnnotationMethodHandlerAdapter, But currently I'm using MockMvc
    
    @Autowired CustomerController customerController;
	
    @Before
    public void setup() {
    	this.mockMvc =  MockMvcBuilders.standaloneSetup(customerController).build();
    }
    
    @Test
    public void findCustomerById_Test() throws Exception {
    	
    	this.mockMvc.perform(get("/customer/1"))
        			.andExpect(status().isOk())
        			.andExpect(content().contentType("application/json"))
        			.andExpect(jsonPath("$.customerName").value("Customer-56"));
    }

}
