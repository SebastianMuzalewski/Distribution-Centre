package com.deez.distribution_center.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.deez.distribution_center.model.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
