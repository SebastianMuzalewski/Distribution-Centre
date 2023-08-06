package com.deez.distribution_center.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name cannot be blank!")
    private String name;
    @Min(value = 2021, message = "Year must be greater than 2021!")
    private int yearCreated;
    @Min(value = 1000, message = "The price must be set higher than $1000!")
    private double price;

    private Brand brandFrom;

    @ManyToOne
    private DistributionCenter distributionCenter;

    @Builder.Default
    private LocalDate createdAt = LocalDate.now();

    @Builder.Default
    private int quantity = 10;

    public enum Brand {
        BALENCIAGA("Balenciaga"), STONE_ISLAND("Stone Island"), DIOR("Dior"), GUCCI("Gucci");

        private String title;

        private Brand(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }
    }

}