package com.ubs.codingTask.repository;

import com.ubs.codingTask.model.DataSnapshot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends CrudRepository<DataSnapshot, Integer> {

}