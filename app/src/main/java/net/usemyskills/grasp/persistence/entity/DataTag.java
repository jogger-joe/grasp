package net.usemyskills.grasp.persistence.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class DataTag {
    @PrimaryKey(autoGenerate = true)
    private int tagId;
    private String name;
    private String description;

    public DataTag(int tagId, String name, String description) {
        this.tagId = tagId;
        this.name = name;
        this.description = description;
    }

    @Ignore
    public DataTag(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Ignore
    public DataTag(String name) {
        this.name = name;
    }

    @Ignore
    public DataTag(int tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
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
