package net.usemyskills.grasp.persistence.entity;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity
public class DataType extends DataTag {
    private String unit;

    public DataType(int id, String name, String description, String unit) {
        super(id, name, description);
        this.unit = unit;
    }

    @Ignore
    public DataType(String name, String description, String unit) {
        super(name, description);
        this.unit = unit;
    }

    @Ignore
    public DataType(String name) {
        super(name);
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

