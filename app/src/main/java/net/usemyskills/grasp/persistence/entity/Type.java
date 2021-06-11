package net.usemyskills.grasp.persistence.entity;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity
public class Type extends Tag {
    public boolean displayAsInteger;
    public String suffix;

    public Type(long tagId, String name, String description, long groupId, boolean displayAsInteger, String suffix) {
        super(tagId, name, description, groupId);
        this.displayAsInteger = displayAsInteger;
        this.suffix = suffix;
    }

    @Ignore
    public Type(String name, String description, long groupId, boolean displayAsInteger, String suffix) {
        this(0, name, description, groupId, displayAsInteger, suffix);
    }

    @Ignore
    public Type(String name, String description) {
        this(name, description, 0, false, "");
    }

    @Ignore
    public Type(String name) {
        this(name, "");
    }

    @Ignore
    public Type() {
        this("", "");
    }
}

