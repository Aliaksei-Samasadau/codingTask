package com.ubs.codingTask.repository;

import com.ubs.codingTask.model.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataSnapshotRepositoryService {

    @Autowired
    EntityRepository entityRepository;

    public List<DataSnapshot> getAllEntities() {
        List<DataSnapshot> dataSnapshots = new ArrayList<>();
        entityRepository.findAll().forEach(dataSnapshots::add);
        return dataSnapshots;
    }

    public DataSnapshot getEntityById(int id) {
        return entityRepository.findById(id).get();
    }

    public void saveOrUpdateAll(List<DataSnapshot> person) {
        entityRepository.saveAll(person);
    }

    public void delete(int id) {
        entityRepository.deleteById(id);
    }
}
