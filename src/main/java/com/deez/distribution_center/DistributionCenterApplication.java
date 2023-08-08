package com.deez.distribution_center;

import com.deez.distribution_center.model.DistributionCenter;
import com.deez.distribution_center.model.Item;
import com.deez.distribution_center.model.Item.Brand;
import com.deez.distribution_center.repository.DBCRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DistributionCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributionCenterApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(DBCRepository dbcRepository) {
		return args -> {
			List<Item> itemsAvailableFor1 = new ArrayList<>();
			itemsAvailableFor1.add(
					Item.builder()
							.name("Juicer Scent")
							.brandFrom(Brand.DIOR)
							.yearCreated(2022)
							.price(1000).build()
			);
			itemsAvailableFor1.add(
					Item.builder()
							.name("Pokimane Handbag")
							.brandFrom(Brand.BALENCIAGA)
							.yearCreated(2021)
							.price(2500).build()
			);
			itemsAvailableFor1.add(
					Item.builder()
							.name("Gamba T-shirt")
							.brandFrom(Brand.STONE_ISLAND)
							.yearCreated(2023)
							.price(2000).build()
			);
			itemsAvailableFor1.add(
					Item.builder()
							.name("Black Shirt")
							.brandFrom(Brand.BALENCIAGA)
							.yearCreated(2021)
							.price(1500).build()
			);
			itemsAvailableFor1.add(
					Item.builder()
							.name("White Shirt")
							.brandFrom(Brand.STONE_ISLAND)
							.yearCreated(2023)
							.price(1500).build()
			);
			dbcRepository.save(DistributionCenter.builder()
					.name("FashionHub Distribution Center")
					.itemsAvailable(itemsAvailableFor1)
					.latitude(15)
					.longitude(-20).build());

			List<Item> itemsAvailableFor2 = new ArrayList<>();
			itemsAvailableFor2.add(
					Item.builder()
							.name("Jeans")
							.brandFrom(Brand.DIOR)
							.yearCreated(2022)
							.price(5000).build()
			);
			itemsAvailableFor2.add(
					Item.builder()
							.name("Pokimane Handbag")
							.brandFrom(Brand.BALENCIAGA)
							.yearCreated(2021)
							.price(2500).build()
			);
			itemsAvailableFor2.add(
					Item.builder()
							.name("Hoodie")
							.brandFrom(Brand.STONE_ISLAND)
							.yearCreated(2025)
							.price(20000).build()
			);
			itemsAvailableFor2.add(
					Item.builder()
							.name("Black Shirt")
							.brandFrom(Brand.BALENCIAGA)
							.yearCreated(2021)
							.price(1500).build()
			);
			itemsAvailableFor2.add(
					Item.builder()
							.name("Red Pants")
							.brandFrom(Brand.BALENCIAGA)
							.yearCreated(2023)
							.price(15000).build()
			);
			dbcRepository.save(DistributionCenter.builder()
					.name("Trendsetter Warehouse")
					.itemsAvailable(itemsAvailableFor2)
					.latitude(89)
					.longitude(20).build());

			List<Item> itemsAvailableFor3 = new ArrayList<>();
			itemsAvailableFor3.add(
					Item.builder()
							.name("Juicer Scent")
							.brandFrom(Brand.DIOR)
							.yearCreated(2022)
							.price(1000).build()
			);
			itemsAvailableFor3.add(
					Item.builder()
							.name("Red Shirt")
							.brandFrom(Brand.STONE_ISLAND)
							.yearCreated(2023)
							.price(10000).build()
			);
			itemsAvailableFor3.add(
					Item.builder()
							.name("Gamba T-shirt")
							.brandFrom(Brand.STONE_ISLAND)
							.yearCreated(2023)
							.price(2000).build()
			);
			itemsAvailableFor3.add(
					Item.builder()
							.name("Black Shirt")
							.brandFrom(Brand.BALENCIAGA)
							.yearCreated(2021)
							.price(1500).build()
			);
			itemsAvailableFor3.add(
					Item.builder()
							.name("Yellow Shirt")
							.brandFrom(Brand.DIOR)
							.yearCreated(2021)
							.price(1000).build()
			);
			dbcRepository.save(DistributionCenter.builder()
					.name("StyleSource Distribution")
					.itemsAvailable(itemsAvailableFor3)
					.latitude(15)
					.longitude(-150).build());

			List<Item> itemsAvailableFor4 = new ArrayList<>();
			itemsAvailableFor4.add(
					Item.builder()
							.name("Juicer Scent")
							.brandFrom(Brand.DIOR)
							.yearCreated(2022)
							.price(1000).build()
			);
			itemsAvailableFor4.add(
					Item.builder()
							.name("Pokimane Handbag")
							.brandFrom(Brand.BALENCIAGA)
							.yearCreated(2021)
							.price(2500).build()
			);
			itemsAvailableFor4.add(
					Item.builder()
							.name("Blue Jeans")
							.brandFrom(Brand.STONE_ISLAND)
							.yearCreated(2023)
							.price(3000).build()
			);
			itemsAvailableFor4.add(
					Item.builder()
							.name("Black Shirt")
							.brandFrom(Brand.BALENCIAGA)
							.yearCreated(2021)
							.price(1500).build()
			);
			itemsAvailableFor4.add(
					Item.builder()
							.name("White Shirt")
							.brandFrom(Brand.STONE_ISLAND)
							.yearCreated(2023)
							.price(1500).build()
			);
			dbcRepository.save(DistributionCenter.builder()
					.name("ChicWear Logistics Center")
					.itemsAvailable(itemsAvailableFor4)
					.latitude(72)
					.longitude(-20).build());

			List<Item> itemsAvailableFor5 = new ArrayList<>();
			itemsAvailableFor5.add(
					Item.builder()
							.name("Blue Jeans")
							.brandFrom(Brand.STONE_ISLAND)
							.yearCreated(2023)
							.price(3000).build()
			);
			itemsAvailableFor5.add(
					Item.builder()
							.name("Pokimane Handbag")
							.brandFrom(Brand.BALENCIAGA)
							.yearCreated(2021)
							.price(2500).build()
			);
			itemsAvailableFor5.add(
					Item.builder()
							.name("Gamba T-shirt")
							.brandFrom(Brand.STONE_ISLAND)
							.yearCreated(2023)
							.price(2000).build()
			);
			itemsAvailableFor5.add(
					Item.builder()
							.name("Black Shirt")
							.brandFrom(Brand.BALENCIAGA)
							.yearCreated(2021)
							.price(1500).build()
			);
			itemsAvailableFor5.add(
					Item.builder()
							.name("Black Jeans")
							.brandFrom(Brand.BALENCIAGA)
							.yearCreated(2022)
							.price(6000).build()
			);
			dbcRepository.save(DistributionCenter.builder()
					.name("ModaConnect Warehouse")
					.itemsAvailable(itemsAvailableFor5)
					.latitude(2)
					.longitude(70).build());

			List<Item> itemsAvailableFor6 = new ArrayList<>();
			itemsAvailableFor6.add(
					Item.builder()
							.name("Black Jeans")
							.brandFrom(Brand.BALENCIAGA)
							.yearCreated(2022)
							.price(6000).build()
			);
			itemsAvailableFor6.add(
					Item.builder()
							.name("Pokimane Handbag")
							.brandFrom(Brand.BALENCIAGA)
							.yearCreated(2021)
							.price(2500).build()
			);
			itemsAvailableFor6.add(
					Item.builder()
							.name("Shorts")
							.brandFrom(Brand.DIOR)
							.yearCreated(2024)
							.price(2000).build()
			);
			itemsAvailableFor6.add(
					Item.builder()
							.name("Green Shorts")
							.brandFrom(Brand.DIOR)
							.yearCreated(2021)
							.price(1000).build()
			);
			itemsAvailableFor6.add(
					Item.builder()
							.name("Red Jeans")
							.brandFrom(Brand.STONE_ISLAND)
							.yearCreated(2022)
							.price(3000).build()
			);
			dbcRepository.save(DistributionCenter.builder()
					.name("FashionLink Logistics Center")
					.itemsAvailable(itemsAvailableFor6)
					.latitude(25)
					.longitude(-170).build());
		};
	}
}
