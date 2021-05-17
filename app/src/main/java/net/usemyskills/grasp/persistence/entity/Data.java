package net.usemyskills.grasp.persistence.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Data {
    @PrimaryKey(autoGenerate = true)
    private int dataId;
    private int dataTypeId;
    private int dataTagId;
    private Date date;
    private int value;

    public Data(int dataId, int dataTypeId, int dataTagId, Date date, int value) {
        this.dataId = dataId;
        this.dataTypeId = dataTypeId;
        this.dataTagId = dataTagId;
        this.date = date;
        this.value = value;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDataTagId() {
        return dataTagId;
    }

    public void setDataTagId(int dataTagId) {
        this.dataTagId = dataTagId;
    }

    public int getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(int dataTypeId) {
        this.dataTypeId = dataTypeId;
    }
}
