package net.usemyskills.grasp.persistence.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity
public class Tag extends BaseEntity {
    private String name;
    private String description;
    private int groupId = 0;

    public Tag(int id, String name, String description, int groupId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.groupId = groupId;
    }

    @Ignore
    public Tag(String name, String description) {
        this(0, name, description, 0);
    }

    @Ignore
    public Tag(String name) {
        this(name, "");
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getName();
    }
}
