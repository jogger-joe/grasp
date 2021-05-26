package net.usemyskills.grasp.persistence.entity;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity
public class RecordGroup extends Tag {
    private String icon;

    public RecordGroup(int id, String name, String description, int groupId, String icon) {
        super(id, name, description, groupId);
        this.icon = icon;
    }

    @Ignore
    public RecordGroup(String name, String description, int groupId, String icon) {
        this(0, name, description, groupId, icon);
    }

    @Ignore
    public RecordGroup(String name, int groupId, String icon) {
        this(name, "", groupId, icon);
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
