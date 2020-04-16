package com.ubs.codingTask.service;

import com.ubs.codingTask.model.DataSnapshot;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DataSnapshotService {
    ResponseEntity<String> upload(MultipartFile file);

    DataSnapshot getEntity(Integer id);

    void deleteEntity(Integer id);

    List<DataSnapshot> getAllEntities();
}
