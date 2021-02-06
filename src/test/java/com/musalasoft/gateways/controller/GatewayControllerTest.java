package com.musalasoft.gateways.controller;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musalasoft.gateways.model.Device;
import com.musalasoft.gateways.model.Gateway;
import com.musalasoft.gateways.service.GatewayService;
import com.musalasoft.gateways.util.Constants;
import java.sql.Timestamp;
import java.time.Instant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(GatewayController.class)
public class GatewayControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    GatewayService gatewayService;
    private Gateway gateway1;
    private Gateway gateway2;
    Device device1;
    Device device2;
    Device device3;
    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void initData() {
        gateway1 = new Gateway("1254638avdfd36", "G1", "192.168.0.1");
        gateway2 = new Gateway("12vcfdefeavdfd", "G2", "192.168.0.2");
        device1 = new Device(563984774, "Samsung", true);
        device2 = new Device(563859489, "Apple", false);
        device3 = new Device(945131331, "Apple", false);
        device1.setCreatedAt(Timestamp.from(Instant.now()));
        device2.setCreatedAt(Timestamp.from(Instant.now()));
        device3.setCreatedAt(Timestamp.from(Instant.now()));

        gateway1.setId(1);
        gateway2.setId(2);
        device1.setGateway(gateway1);
        device2.setGateway(gateway1);
        device3.setGateway(gateway2);
    }

    @Test
    public void getGatewayByIdSuccessTest() throws Exception {
        when(gatewayService.getGateway(1)).thenReturn(gateway1);
        mockMvc
            .perform(
                get(
                    "/api/gateways/{id}",
                    1)
                    .contextPath(
                        "/api") /* context path must be set to precede the api uri*/
                    .contentType("application/json"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is("G1")))
            .andExpect(jsonPath("$.ip", is("192.168.0.1")));
    }

    @Test
    public void getGatewayByIdNotFoundTest() throws Exception {
        when(gatewayService.getGateway(1)).thenReturn(null);
        mockMvc
            .perform(
                get(
                    "/api/gateways/{id}",
                    1)
                    .contextPath(
                        "/api") /* context path must be set to precede the api uri*/
                    .contentType("application/json"))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getGatewayByIdExceptionTest() throws Exception {
        when(gatewayService.getGateway(1)).
            thenThrow(new RuntimeException());
        mockMvc
            .perform(
                get(
                    "/api/gateways/{id}",
                    1)
                    .contextPath(
                        "/api") /* context path must be set to precede the api uri*/
                    .contentType("application/json"))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createGatewaySuccessTest() throws Exception {
        String body = mapper.writeValueAsString(gateway1);
        when(gatewayService.createGateway(gateway1)).thenReturn(gateway1);
        mockMvc
            .perform(
                post(
                    "/api/gateways")
                    .contextPath("/api") /* context path must be set to precede the api uri*/
                    .contentType("application/json")
                    .content(body))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is("G1")))
            .andExpect(jsonPath("$.ip", is("192.168.0.1")));
    }

    @Test
    public void createGatewayValidationTest() throws Exception {
        gateway1.setIp("192.168.0.366");
        String body = mapper.writeValueAsString(gateway1);
        when(gatewayService.createGateway(gateway1)).thenReturn(gateway1);
        mockMvc
            .perform(
                post(
                    "/api/gateways")
                    .contextPath("/api") /* context path must be set to precede the api uri*/
                    .contentType("application/json")
                    .content(body))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error", endsWith(Constants.INVALID_VALUE)));
    }

}
