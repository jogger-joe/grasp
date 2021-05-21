package net.usemyskills.grasp.persistence.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.util.Date;

@Entity
public class Data extends BaseEntity {
    private int dataTypeId;
    private int dataTagId;
    private Date date;
    private int value;

    public Data(int id, int dataTypeId, int dataTagId, Date date, int value) {
        this.id = id;
        this.dataTypeId = dataTypeId;
        this.dataTagId = dataTagId;
        this.date = date;
        this.value = value;
    }

    @Ignore
    public Data(int dataTypeId, int dataTagId, Date date, int value) {
        this.dataTypeId = dataTypeId;
        this.dataTagId = dataTagId;
        this.date = date;
        this.value = value;
    }

    @Ignore
    public Data(Date date, int value) {
        this.date = date;
        this.value = value;
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
