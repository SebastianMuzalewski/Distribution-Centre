package com.deez.distribution_center;

import com.deez.distribution_center.model.DistributionCenter;
import com.deez.distribution_center.repository.DBCRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DistributionCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributionCenterApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(DBCRepository dbcRepository) {
		return args -> {
			dbcRepository.save(DistributionCenter.builder()
					.name("FashionHub Distribution Center")
					.itemsAvailable(200)
					.latitude(15)
					.longitude(-20).build());
			dbcRepository.save(DistributionCenter.builder()
					.name("Trendsetter Warehouse")
					.itemsAvailable(120)
					.latitude(89)
					.longitude(20).build());
			dbcRepository.save(DistributionCenter.builder()
					.name("StyleSource Distribution")
					.itemsAvailable(80)
					.latitude(15)
					.longitude(-150).build());
			dbcRepository.save(DistributionCenter.builder()
					.name("ChicWear Logistics Center")
					.itemsAvailable(220)
					.latitude(72)
					.longitude(-20).build());
			dbcRepository.save(DistributionCenter.builder()
					.name("ModaConnect Warehouse")
					.itemsAvailable(500)
					.latitude(2)
					.longitude(70).build());
			dbcRepository.save(DistributionCenter.builder()
					.name("FashionLink Logistics Center")
					.itemsAvailable(250)
					.latitude(25)
					.longitude(-170).build());
		};
	}
}
