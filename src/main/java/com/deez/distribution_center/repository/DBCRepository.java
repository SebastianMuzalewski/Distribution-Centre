package com.deez.distribution_center.repository;

import com.deez.distribution_center.model.DistributionCenter;
import com.deez.distribution_center.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DBCRepository extends CrudRepository<Item, Long> {
    List<DistributionCenter> DBCFindByName(String name);

}
