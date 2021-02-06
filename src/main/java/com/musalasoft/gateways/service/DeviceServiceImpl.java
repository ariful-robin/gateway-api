package com.musalasoft.gateways.service;

import com.musalasoft.gateways.model.Device;
import com.musalasoft.gateways.model.Gateway;
import com.musalasoft.gateways.repository.DeviceRepository;
import com.musalasoft.gateways.util.Constants;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final GatewayService gatewayService;

    @Override
    public Device createDevice(Device device, Long gatewayId) {
        if (Objects.isNull(device) || Objects.isNull(gatewayId)) {
            return null;
        }
        Gateway gateway = gatewayService.getGateway(gatewayId);
        if (Objects.isNull(gateway)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "No parent gateway found with this id");
        }
        if (gateway.getDevices().size() > Constants.MAX_DEVICES) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Gateway already has max number of devices");
        }

        long uid = device.getUid();
        Optional<Device> deviceOptional = deviceRepository.getDeviceByUid(uid);
        if (deviceOptional.isPresent()) {
            Device existingDevice = deviceOptional.get();
            existingDevice.setUid(device.getUid());
            existingDevice.setVendor(device.getVendor());
            existingDevice.setStatus(device.isStatus());
            existingDevice.setGateway(gateway);
            deviceRepository.save(device);
            return existingDevice;
        } else {
            device.setCreatedAt(Timestamp.from(Instant.now()));
            device.setGateway(gateway);
            return deviceRepository.save(device);
        }
    }

    @Override
    public Device getDeviceById(Long gatewayId, Long id) {
        Device device = deviceRepository.getDeviceByIdAndGatewayId(id, gatewayId).orElse(null);
        if (Objects.isNull(device)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No device found under the gateway");
        }
        return device;
    }

    @Override
    public List<Device> getAllDevices(Long gatewayId) {
        return deviceRepository.getDeviceByGatewayId(gatewayId);
    }

    @Override
    @Transactional
    public void deleteDevice(Long gatewayId, Long id) {
        deviceRepository.deleteDeviceByIdAndGatewayId(id, gatewayId);
    }

}
