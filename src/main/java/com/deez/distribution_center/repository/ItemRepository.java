package com.deez.distribution_center.repository;

import com.deez.distribution_center.model.Item;
import com.deez.distribution_center.model.Item.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByBrandFrom(Brand brandFrom);
    List<Item> findByName(String name);
    List<Item> findByNameContainingAndBrandFrom(String name, Brand brandFrom);
}
