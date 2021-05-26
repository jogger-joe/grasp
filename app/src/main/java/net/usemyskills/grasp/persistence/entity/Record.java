package net.usemyskills.grasp.persistence.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.util.Date;

@Entity
public class Record extends BaseEntity {
    private int typeId = 0;
    private int groupId = 0;
    private Date date;
    private double value = 0;

    public Record(int id, int typeId, int groupId, Date date, double value) {
        this.id = id;
        this.typeId = typeId;
        this.groupId = groupId;
        this.date = date;
        this.value = value;
    }

    @Ignore
    public Record(int typeId, int groupId, Date date, double value) {
        this(0, typeId, groupId, date, value);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
