package com.musalasoft.gateways.service;

import com.musalasoft.gateways.model.Device;
import java.util.List;

public interface DeviceService {

    Device createDevice(Device device, Long gatewayId);

    Device getDeviceById(Long gatewayId, Long id);

    List<Device> getAllDevices(Long gatewayId);

    void deleteDevice(Long gatewayId, Long id);

}
