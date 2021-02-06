package com.musalasoft.gateways.controller;

import com.musalasoft.gateways.model.Device;
import com.musalasoft.gateways.service.DeviceService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/gateways/{gatewayId}/devices/{id}")
    public ResponseEntity<Object> getDeviceById(@PathVariable Long gatewayId,
        @PathVariable Long id) {
        try{
            Device device = deviceService.getDeviceById(gatewayId, id);
            return new ResponseEntity<>(device, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting device", e);
        }
    }

    @GetMapping("/gateways/{gatewayId}/devices")
    public ResponseEntity<Object> getAllDevices(@PathVariable Long gatewayId) {
        try{
            List<Device> deviceList = deviceService.getAllDevices(gatewayId);
            return new ResponseEntity<>(deviceList, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting all device", e);
        }
    }

    @PostMapping("/gateways/{gatewayId}/devices")
    public ResponseEntity<Object> createDevice(@Valid @RequestBody Device device,
        @PathVariable Long gatewayId) {
        try {
            Device createdDevice = deviceService.createDevice(device, gatewayId);
            return new ResponseEntity<>(createdDevice, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating device", e);
        }
    }
    @DeleteMapping("/gateways/{gatewayId}/devices/{id}")
    public ResponseEntity<Object> deleteDeviceById(@PathVariable Long gatewayId,
        @PathVariable Long id) {
        try{
            deviceService.deleteDevice(gatewayId, id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting device", e);
        }
    }

}
