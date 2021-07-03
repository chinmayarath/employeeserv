package com.paypal.bfs.test.employeeserv.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paypal.bfs.test.employeeserv.EmployeeservApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmployeeservApplication.class})
@AutoConfigureMockMvc
public class EmployeeServiceTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void test_create_employee_with_valid_payload() throws Exception {
		mvc.perform(post("/v1/bfs/employees").contentType(MediaType.APPLICATION_JSON).content(TEST_STRING))
				.andExpect(status().isCreated());
	}

	@Test
	public void test_create_employee_with_Invalid_payload() throws Exception {
		JsonNode node = new ObjectMapper().readTree(TEST_STRING);
		((ObjectNode) node).remove("dob");
		mvc.perform(post("/v1/bfs/employees").contentType(MediaType.APPLICATION_JSON).content(node.toString()))
				.andExpect(status().isBadRequest());
	}

	/**
	 * 
	 */
	private static final String TEST_STRING = "{\"first_name\":\"sds\",\"last_name\":\"sds\",\"dob\":\"12/07/2012\",\"address\":{\"line1\":\"sds\",\"state\":\"od\",\"country\":\"india\",\"zip\":\"sds\",\"city\":\"sds\"}}";
}
