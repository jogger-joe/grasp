package net.usemyskills.grasp.persistence.entity;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity
public class DataTypeTag extends DataTag {
    private int modifier;
    private String unit;

    public DataTypeTag(int tagId, String name, String description, int modifier, String unit) {
        super(tagId, name, description);
        this.modifier = modifier;
        this.unit = unit;
    }

    @Ignore
    public DataTypeTag(int tagId, String name) {
        super(tagId, name);
        this.modifier = 1;
        this.unit = "x";
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

