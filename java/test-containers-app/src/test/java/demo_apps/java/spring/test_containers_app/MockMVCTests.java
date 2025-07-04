package demo_apps.java.spring.test_containers_app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import demo_apps.java.spring.test_containers_app.controllers.CustomerController;

/** Tests for the CustomerController using MockMvc. */
@WebMvcTest(CustomerController.class)
public class MockMVCTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testHelloEndpoint() throws Exception {
		mockMvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string("Hello, World!"));
	}
}