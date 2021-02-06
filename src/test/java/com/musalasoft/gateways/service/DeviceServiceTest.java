package com.musalasoft.gateways.service;


import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musalasoft.gateways.model.Device;
import com.musalasoft.gateways.model.Gateway;
import com.musalasoft.gateways.repository.DeviceRepository;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class DeviceServiceTest {

    @InjectMocks()
    DeviceServiceImpl deviceService;

    @Mock
    DeviceRepository deviceRepository;

    @Mock
    GatewayService gatewayService;

    ObjectMapper mapper;

    Gateway gateway;
    Set<Device> devices;
    Device device;

    @Before
    public void initData() {
        mapper = new ObjectMapper();
        gateway = new Gateway("1254638avdfd36", "G1", "192.168.0.1");
        gateway.setId(1);
        device = new Device(563984774, "Samsung", true);
        devices = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            long uid = device.getUid();
            devices.add(device);
            device.setUid(uid + 1);
        }
        gateway.setDevices(devices);
    }


    @Test(expected = ResponseStatusException.class)
    public void createDeviceExceedingMaxTest() {
        device.setUid(125363691);
        when(gatewayService.getGateway(gateway.getId())).thenReturn(gateway);
        when(deviceService.createDevice(device, gateway.getId())).thenCallRealMethod();
        deviceService.createDevice(device, gateway.getId());
    }

}
