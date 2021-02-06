package com.musalasoft.gateways;

import com.musalasoft.gateways.model.Device;
import com.musalasoft.gateways.model.Gateway;
import com.musalasoft.gateways.repository.DeviceRepository;
import com.musalasoft.gateways.repository.GatewayRepository;
import java.sql.Timestamp;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(
        GatewayRepository gatewayRepository, DeviceRepository deviceRepository) {

        Gateway gateway1 = new Gateway("1254638avdfd36", "G1", "192.168.0.1");
        Gateway gateway2 = new Gateway("12vcfdefeavdfd", "G2", "192.168.0.2");
        Device device1 = new Device(563984774,"Samsung", true);
        Device device2 = new Device(563859489,"Apple", false);
        Device device3 = new Device(945131331,"Apple", false);
        device1.setCreatedAt(Timestamp.from(Instant.now()));
        device2.setCreatedAt(Timestamp.from(Instant.now()));
        device3.setCreatedAt(Timestamp.from(Instant.now()));

        device1.setGateway(gateway1);
        device2.setGateway(gateway1);
        device3.setGateway(gateway2);

        return args -> {
            log.info("Preloading Gateway " + gatewayRepository.save(gateway1));
            log.info("Preloading Gateway" + gatewayRepository.save(gateway2));
            log.info("Preloading Device" + deviceRepository.save(device1));
            log.info("Preloading Device" + deviceRepository.save(device2));
            log.info("Preloading Device" + deviceRepository.save(device3));
            log.info("Test");
        };
    }
}
