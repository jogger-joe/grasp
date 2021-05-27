package net.usemyskills.grasp.persistence.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Tag extends BaseEntity {
    @PrimaryKey(autoGenerate = true)
    public long tagId;
    public String name;
    public String description;
    public long groupId = 0;

    public Tag(long tagId, String name, String description, long groupId) {
        this.tagId = tagId;
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
}
