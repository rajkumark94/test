package com.sample.hibernate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.hibernate.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
