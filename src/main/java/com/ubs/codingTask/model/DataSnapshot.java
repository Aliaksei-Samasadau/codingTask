package com.ubs.codingTask.model;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DataSnapshot {
    @Id
    @CsvBindByName(column = "PRIMARY_KEY")
    private Integer primaryKey;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String description;
    @CsvBindByName(column = "UPDATED_TIMESTAMP")
    private String updatedTimestamp;

    public DataSnapshot() {
    }

    public DataSnapshot(Integer primaryKey, String name, String description, String updatedTimestamp) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.description = description;
        this.updatedTimestamp = updatedTimestamp;
    }

    public Integer getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Integer primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(String updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }
}
