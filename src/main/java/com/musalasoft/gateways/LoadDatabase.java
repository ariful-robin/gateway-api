package com.musalasoft.gateways;

import com.musalasoft.gateways.model.Device;
import com.musalasoft.gateways.model.Gateway;
import com.musalasoft.gateways.repository.DeviceRepository;
import com.musalasoft.gateways.repository.GatewayRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
        Device device1 = new Device(UUID.fromString("d18cc521-a069-44ba-a635-9547b4e02951"),
            "Samsung", true);
        Device device2 = new Device(UUID.fromString("af78f822-2b7a-4f00-9d3f-d5ea5b72c593"),
            "Apple", false);
        Device device3 = new Device(UUID.fromString("af78f822-2b7a-4f00-9d3f-d5ea5b72c562"),
            "Apple", false);

        device1.setGateway(gateway1);
        device2.setGateway(gateway1);
        device3.setGateway(gateway2);

        return args -> {
            log.info("Preloading Gateway " + gatewayRepository.save(gateway1));
            log.info("Preloading Gateway" + gatewayRepository.save(gateway2));
            log.info("Preloading Device" + deviceRepository.save(device1));
            log.info("Preloading Device" + deviceRepository.save(device2));
            log.info("Preloading Device" + deviceRepository.save(device3));

            List<Gateway> gatewayList = new ArrayList<>();
            gatewayList = gatewayRepository.findAll();

            log.info("Fetch Data From Database: ");

        };
    }
}
