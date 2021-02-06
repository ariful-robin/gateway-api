package com.musalasoft.gateways.service;

import com.musalasoft.gateways.model.Gateway;
import com.musalasoft.gateways.repository.GatewayRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GatewayServiceImpl implements GatewayService {

    private final GatewayRepository gatewayRepository;

    @Override
    public Gateway createGateway(Gateway gateway) {
        if (Objects.isNull(gateway)) {
            return null;
        }

        String serialNo = gateway.getSerialNo();
        Optional<Gateway> gatewayOptional = gatewayRepository.findGatewayBySerialNo(serialNo);
        if (gatewayOptional.isPresent()) {
            Gateway existingGateway = gatewayOptional.get();
            existingGateway.setSerialNo(gateway.getSerialNo());
            existingGateway.setName(gateway.getName());
            existingGateway.setIp(gateway.getIp());
            gatewayRepository.save(existingGateway);
            return existingGateway;
        } else {
            return gatewayRepository.save(gateway);
        }

    }

    @Override
    public Gateway getGateway(long id) {
        Optional<Gateway> gatewayOptional = gatewayRepository.findById(id);
        return gatewayOptional.orElse(null);

    }

    @Override
    public List<Gateway> getAllGateway() {
        return gatewayRepository.findAll();
    }
}
