package com.ubs.codingTask.service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.ubs.codingTask.repository.DataSnapshotRepositoryService;
import com.ubs.codingTask.model.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class DataSnapshotServiceImpl implements DataSnapshotService {

    @Autowired
    DataSnapshotRepositoryService dataSnapshotRepositoryService;

    @Override
    public ResponseEntity<String> upload(MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            String[] columns = {"primaryKey", "name", "description", "updatedTimestamp"};

            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(DataSnapshot.class);
            strategy.setColumnMapping(columns);

            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withMappingStrategy(strategy)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<DataSnapshot> parse = csvToBean.parse();
            List<DataSnapshot> validaDataSnapshot = filterIncorrectRows(parse);
            dataSnapshotRepositoryService.saveOrUpdateAll(validaDataSnapshot);

        } catch (IOException ex) {
            return ResponseEntity.badRequest().body("Can't read the file");
        }
        return ResponseEntity.ok().body("File uploaded");
    }

    private List<DataSnapshot> filterIncorrectRows(List<DataSnapshot> parse) {

        Predicate<DataSnapshot> isValidEntity = dataSnapshot ->
                !StringUtils.isEmpty(dataSnapshot.getName()) &&
                        dataSnapshot.getPrimaryKey() != null &&
                        !StringUtils.isEmpty(dataSnapshot.getDescription()) &&
                        !StringUtils.isEmpty(dataSnapshot.getUpdatedTimestamp());
        return parse
                .stream()
                .filter(isValidEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DataSnapshot getEntity(Integer id) {
        return dataSnapshotRepositoryService.getEntityById(id);
    }

    @Override
    public void deleteEntity(Integer id) {
        dataSnapshotRepositoryService.delete(id);
    }

    @Override
    public List<DataSnapshot> getAllEntities() {
        return dataSnapshotRepositoryService.getAllEntities();
    }
}
