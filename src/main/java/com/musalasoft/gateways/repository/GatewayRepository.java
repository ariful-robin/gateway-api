package com.musalasoft.gateways.repository;

import com.musalasoft.gateways.model.Gateway;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway, Long> {

    Optional<Gateway> findGatewayByUid(String uid);
}
