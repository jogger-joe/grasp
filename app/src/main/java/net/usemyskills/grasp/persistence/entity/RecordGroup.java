package net.usemyskills.grasp.persistence.entity;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity
public class RecordGroup extends Tag {
    public int iconId;

    public RecordGroup(long tagId, String name, String description, long groupId, int iconId) {
        super(tagId, name, description, groupId);
        this.iconId = iconId;
    }

    @Ignore
    public RecordGroup(String name, String description, int iconId) {
        this(0, name, description, 0, iconId);
    }

    @Ignore
    public RecordGroup(String name, int iconId) {
        this(name, "", iconId);
    }

    @Ignore
    public RecordGroup() {
        super();
    }
}
