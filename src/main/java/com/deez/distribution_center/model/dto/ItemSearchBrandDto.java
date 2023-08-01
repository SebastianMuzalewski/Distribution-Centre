package com.deez.distribution_center.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemSearchBrandDto {
    private String brandFrom;
    private String name;
}