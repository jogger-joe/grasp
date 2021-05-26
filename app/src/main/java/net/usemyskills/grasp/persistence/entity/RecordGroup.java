package net.usemyskills.grasp.persistence.entity;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity
public class RecordGroup extends Tag {
    public String icon;

    public RecordGroup(long tagId, String name, String description, long groupId, String icon) {
        super(tagId, name, description, groupId);
        this.icon = icon;
    }

    @Ignore
    public RecordGroup(String name, String description, long groupId, String icon) {
        this(0, name, description, groupId, icon);
    }

    @Ignore
    public RecordGroup(String name, long groupId, String icon) {
        this(name, "", groupId, icon);
    }
}
