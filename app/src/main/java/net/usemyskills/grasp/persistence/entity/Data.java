package net.usemyskills.grasp.persistence.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.util.Date;

@Entity
public class Data extends BaseEntity {
    private int typeId = 0;
    private int tagId = 0;
    private Date date;
    private int value = 0;

    public Data(int id, int typeId, int tagId, Date date, int value) {
        this.id = id;
        this.typeId = typeId;
        this.tagId = tagId;
        this.date = date;
        this.value = value;
    }

    @Ignore
    public Data(int typeId, int tagId, Date date, int value) {
        this.typeId = typeId;
        this.tagId = tagId;
        this.date = date;
        this.value = value;
    }

    @Ignore
    public Data(int id, Date date, int value) {
        this.id = id;
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

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
