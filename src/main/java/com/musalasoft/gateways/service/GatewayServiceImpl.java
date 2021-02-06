package com.musalasoft.gateways.service;

import com.musalasoft.gateways.model.Gateway;
import com.musalasoft.gateways.repository.GatewayRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GatewayServiceImpl implements GatewayService {

    private final GatewayRepository gatewayRepository;

    @Override
    public Gateway createGateway(Gateway gateway) {
        if(Objects.isNull(gateway)){
            return null;
        }
        String uid = gateway.getUid();
        try {
            Optional<Gateway> gatewayOptional = gatewayRepository.findGatewayByUid(uid);
            if (gatewayOptional.isPresent()) {
                Gateway existingGateway = gatewayOptional.get();
                existingGateway.setUid(gateway.getUid());
                existingGateway.setName(gateway.getName());
                existingGateway.setIp(gateway.getIp());
                gatewayRepository.save(existingGateway);
                return existingGateway;
            } else {
                return gatewayRepository.save(gateway);
            }
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public Gateway getGateway(String uid) {
        Optional<Gateway> gatewayOptional = gatewayRepository.findGatewayByUid(uid);
        return gatewayOptional.orElse(null);

    }

    @Override
    public List<Gateway> getAllGateway() {
        return null;
    }

    @Override
    public void deleteGateway(String uuid) {

    }
}
