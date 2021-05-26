package net.usemyskills.grasp.persistence.entity;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity
public class Type extends Tag {
    private String format;
    private String suffix;

    public Type(long tagId, String name, String description, long groupId, String format, String suffix) {
        super(tagId, name, description, groupId);
        this.format = format;
        this.suffix = suffix;
    }

    @Ignore
    public Type(String name, String description, long groupId, String format, String suffix) {
        this(0, name, description, groupId, format, suffix);
    }

    @Ignore
    public Type(String name, String description) {
        this(name, description, 0, "", "");
    }

    @Ignore
    public Type(String name) {
        this(name, "");
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}

