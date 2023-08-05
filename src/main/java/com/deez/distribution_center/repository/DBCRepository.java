package com.deez.distribution_center.repository;

import com.deez.distribution_center.model.DistributionCenter;
import com.deez.distribution_center.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBCRepository extends CrudRepository<DistributionCenter, Long> {
    List<DistributionCenter> findByName(String name);

}
