package com.musalasoft.gateways.controller;

import com.musalasoft.gateways.model.Gateway;
import com.musalasoft.gateways.service.GatewayService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class GatewayController {

    private final GatewayService gatewayService;

    @PostMapping("/gateways")
    public ResponseEntity<Object> createGateway(@Valid @RequestBody Gateway gateway) {
        try {
            Gateway createdGateway = gatewayService.createGateway(gateway);
            return new ResponseEntity<>(createdGateway, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
