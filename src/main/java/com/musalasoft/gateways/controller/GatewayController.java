package com.musalasoft.gateways.controller;

import com.musalasoft.gateways.model.Gateway;
import com.musalasoft.gateways.service.GatewayService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Objects;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class GatewayController {

    private final GatewayService gatewayService;

    @ApiOperation("Get details of a gateway")
    @ApiResponses(
        value = {
            @ApiResponse(code = 200, message = "Got details of a gateway", response = Gateway.class)
        })
    @GetMapping("/gateways/{id}")
    public ResponseEntity<Object> getGatewayById(@PathVariable long id) {
        try {
            Gateway gateway = gatewayService.getGateway(id);
            if (Objects.isNull(gateway)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No gateway found for this id");
            }
            return new ResponseEntity<>(gateway, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Error getting gateway by this id", e);
        }
    }

    @ApiOperation("Create a new gateway")
    @ApiResponses(
        value = {
            @ApiResponse(code = 201, message = "Gateway created successfully", response = Gateway.class)
        })
    @PostMapping("/gateways")
    public ResponseEntity<Object> createGateway(@Valid @RequestBody Gateway gateway) {
        try {
            Gateway createdGateway = gatewayService.createGateway(gateway);
            return new ResponseEntity<>(createdGateway, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating gateway", e);
        }
    }

    @ApiOperation("List all gateways")
    @ApiResponses(
        value = {
            @ApiResponse(code = 200, message = "Got all gateways", response = Gateway[].class)
        })
    @GetMapping("/gateways")
    public ResponseEntity<Object> getAllGateways() {
        try {
            return new ResponseEntity<>(gatewayService.getAllGateway(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting all gateways",
                e);
        }
    }
}
