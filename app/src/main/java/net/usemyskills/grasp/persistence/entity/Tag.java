package net.usemyskills.grasp.persistence.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import net.usemyskills.grasp.model.Selectable;

@Entity
public class Tag extends BaseEntity implements Selectable {
    private String name;
    private String description;

    public Tag(int id, String name, String description) {
        this(name, description);
        this.id = id;
    }

    @Ignore
    public Tag(String name) {
        this(name, "");
    }

    @Ignore
    public Tag(String name, String description) {
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

    @Override
    public String getLabel() {
        return this.getName();
    }
}
