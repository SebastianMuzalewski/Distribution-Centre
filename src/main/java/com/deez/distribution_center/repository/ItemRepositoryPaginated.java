package com.deez.distribution_center.repository;

import com.deez.distribution_center.model.Item;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepositoryPaginated extends PagingAndSortingRepository<Item, Long> {
}
