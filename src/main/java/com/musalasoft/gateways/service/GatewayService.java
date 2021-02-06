package com.musalasoft.gateways.service;

import com.musalasoft.gateways.model.Gateway;
import java.util.List;

public interface GatewayService {

    Gateway createGateway(Gateway gateway);

    Gateway getGateway(long id);

    List<Gateway> getAllGateway();
}
