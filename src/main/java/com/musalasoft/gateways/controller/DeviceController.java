package com.musalasoft.gateways.controller;

import com.musalasoft.gateways.model.Device;
import com.musalasoft.gateways.service.DeviceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation("Get a device under a gateway")
    @ApiResponses(
        value = {
            @ApiResponse(code = 200, message = "Got details of a device", response = Device.class)
        })
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

    @ApiOperation("List all devices under a gateway")
    @ApiResponses(
        value = {
            @ApiResponse(code = 200, message = "Got all devices under a gateway", response = Device[].class)
        })
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

    @ApiOperation("Create device under a gateway")
    @ApiResponses(
        value = {
            @ApiResponse(code = 201, message = "Device created successfully", response = Device.class)
        })
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

    @ApiOperation("Delete a device under a gateway")
    @ApiResponses(
        value = {
            @ApiResponse(code = 204, message = "Device deleted successfully")
        })
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
