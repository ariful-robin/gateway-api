package com.musalasoft.gateways.repository;

import com.musalasoft.gateways.model.Device;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> getDeviceByUid(Long uid);

    Optional<Device> getDeviceByIdAndGatewayId(Long id, Long gatewayId);

    List<Device> getDeviceByGatewayId(Long gatewayId);

    void deleteDeviceByIdAndGatewayId(Long id, Long gatewayId);
}
