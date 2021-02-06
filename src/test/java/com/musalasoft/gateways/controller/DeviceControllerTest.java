package com.musalasoft.gateways.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musalasoft.gateways.model.Device;
import com.musalasoft.gateways.model.Gateway;
import com.musalasoft.gateways.service.DeviceService;
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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

@RunWith(SpringRunner.class)
@WebMvcTest
public class DeviceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DeviceService deviceService;

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
        device1.setId(1);
        device2.setId(2);
        device3.setId(3);
        device1.setGateway(gateway1);
        device2.setGateway(gateway1);
        device3.setGateway(gateway2);
    }

    @Test
    public void getDeviceByIdSuccessTest() throws Exception {
        when(deviceService.getDeviceById(gateway1.getId(), device1.getId())).thenReturn(device1);
        mockMvc
            .perform(
                get(
                    "/api/gateways/{gatewayId}/devices/{id}",
                    gateway1.getId(), device1.getId())
                    .contextPath(
                        "/api") /* context path must be set to precede the api uri*/
                    .contentType("application/json"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.vendor", is(device1.getVendor())))
            .andExpect(jsonPath("$.status", is(device1.isStatus())));
    }

    @Test
    public void getDeviceByIdErrorTest() throws Exception {
        when(deviceService.getDeviceById(gateway1.getId(), device1.getId()))
            .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND,
                Constants.NO_DEVICE_FOUND));
        mockMvc
            .perform(
                get(
                    "/api/gateways/{gatewayId}/devices/{id}",
                    gateway1.getId(), device1.getId())
                    .contextPath(
                        "/api") /* context path must be set to precede the api uri*/
                    .contentType("application/json"))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.error", is(Constants.NO_DEVICE_FOUND)));
    }

}
