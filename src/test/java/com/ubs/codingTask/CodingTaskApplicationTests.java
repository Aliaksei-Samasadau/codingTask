package com.ubs.codingTask;

import com.ubs.codingTask.model.DataSnapshot;
import com.ubs.codingTask.repository.DataSnapshotRepositoryService;
import com.ubs.codingTask.service.DataSnapshotService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class CodingTaskApplicationTests {
    private static final List<DataSnapshot> TEST_DATA = Arrays.asList(
            new DataSnapshot(1, "name_1", "desc_1", "2016-11-09 11:44:22"),
            new DataSnapshot(2, "name_2", "desc_2", "2016-11-09 11:44:44"));

    @Autowired
    private DataSnapshotService dataSnapshotService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private DataSnapshotRepositoryService dataSnapshotRepositoryService;

    @Test
    void upload() throws Exception {
        MockMultipartFile mockMultipartFile =
                new MockMultipartFile("fileName", "file.csv", "text/plain",
                        new FileInputStream("/Users/asamasadau/Downloads/codingTask/src/test/resources/file.csv"));

        this.mvc.perform(fileUpload("/upload").file(mockMultipartFile))
                .andExpect(status().isOk());
    }

    @Test
    void uploadWithOneIncorrectRecord() throws Exception {
        MockMultipartFile mockMultipartFile =
                new MockMultipartFile("fileName", "file_with_incorrect_record.csv", "text/plain",
                        new FileInputStream("/Users/asamasadau/Downloads/codingTask/src/test/resources/file_with_incorrect_record.csv"));
        this.mvc.perform(fileUpload("/upload").file(mockMultipartFile));
        assertThat(dataSnapshotService.getAllEntities().size()).isEqualTo(2);
    }

    @Test
    void getAllEntities() throws Exception {
        dataSnapshotRepositoryService.saveOrUpdateAll(TEST_DATA);
        assertThat(dataSnapshotService.getAllEntities().size()).isEqualTo(TEST_DATA.size());
    }

    @Test
    void getEntity() {
        dataSnapshotRepositoryService.saveOrUpdateAll(TEST_DATA);
        assertThat(dataSnapshotService.getEntity(1).getName()).isEqualTo(TEST_DATA.get(0).getName());
        assertThat(dataSnapshotService.getEntity(1).getDescription()).isEqualTo(TEST_DATA.get(0).getDescription());
        assertThat(dataSnapshotService.getEntity(1).getUpdatedTimestamp()).isEqualTo(TEST_DATA.get(0).getUpdatedTimestamp());
        assertThat(dataSnapshotService.getEntity(1).getPrimaryKey()).isEqualTo(TEST_DATA.get(0).getPrimaryKey());
    }

    @Test
    void deleteEntity() {
        dataSnapshotRepositoryService.saveOrUpdateAll(TEST_DATA);
        dataSnapshotRepositoryService.delete(1);
        assertThat(dataSnapshotService.getAllEntities().size()).isEqualTo(1);
    }
}
