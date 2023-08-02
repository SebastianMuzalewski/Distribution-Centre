package com.deez.distribution_center.repository;

import com.deez.distribution_center.model.DistributionCenter;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBCRepositoryPaginated extends PagingAndSortingRepository<DistributionCenter, Long> {
}
