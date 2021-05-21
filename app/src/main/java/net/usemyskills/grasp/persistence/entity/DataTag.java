package net.usemyskills.grasp.persistence.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity
public class DataTag extends BaseEntity {
    private String name;
    private String description;

    public DataTag(int id, String name, String description) {
        this(name, description);
        this.id = id;
    }

    @Ignore
    public DataTag(String name) {
        this(name, "");
    }

    @Ignore
    public DataTag(String name, String description) {
        this.name = name;
        this.description = description;
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

    @NonNull
    @Override
    public String toString() {
        return this.getName();
    }
}
