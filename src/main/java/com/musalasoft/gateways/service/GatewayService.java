package com.musalasoft.gateways.service;

import com.musalasoft.gateways.model.Gateway;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

public interface GatewayService {
    Gateway createGateway(Gateway gateway);
    Gateway getGateway(String uid);
    List<Gateway> getAllGateway();
    void deleteGateway(String uuid);
}
