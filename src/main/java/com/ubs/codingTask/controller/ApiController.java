package com.ubs.codingTask.controller;

import com.ubs.codingTask.service.DataSnapshotService;
import com.ubs.codingTask.model.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ApiController {

    @Autowired
    private DataSnapshotService snapshotService;

    @PostMapping(path = "/upload")
    public ResponseEntity<String> upload(@RequestParam("fileName") @Valid MultipartFile file) {
        return snapshotService.upload(file);
    }

    @GetMapping("/entity/{id}")
    private ResponseEntity<DataSnapshot> getEntity(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok().body(snapshotService.getEntity(id));
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/entity/{id}")
    private void deleteEntity(@Valid @PathVariable(value = "id") Integer id) {
            snapshotService.deleteEntity(id);
    }

    @GetMapping("/entities")
    private List<DataSnapshot> getAllEntities() {
        return snapshotService.getAllEntities();
    }
}