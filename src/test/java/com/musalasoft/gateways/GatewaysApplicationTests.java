package com.musalasoft.gateways;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.musalasoft.gateways.model.Gateway;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GatewaysApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void smokeTest() {
		Gateway gateway = new Gateway("1254638avdfd36", "G1", "192.168.0.1");
		assertTrue(gateway.getName().equals("G1"));
	}

}
