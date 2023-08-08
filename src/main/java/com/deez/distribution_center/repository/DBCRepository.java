package com.deez.distribution_center.repository;

import com.deez.distribution_center.model.DistributionCenter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DBCRepository extends CrudRepository<DistributionCenter, Long> {
    List<DistributionCenter> findByName(String name);
}
