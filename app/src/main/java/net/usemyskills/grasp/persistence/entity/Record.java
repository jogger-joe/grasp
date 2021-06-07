package net.usemyskills.grasp.persistence.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Record extends BaseEntity {
    @PrimaryKey(autoGenerate = true)
    public long recordId;
    public long typeId;
    public long groupId;
    public Date date;
    public double value;

    public Record(long recordId, long typeId, long groupId, Date date, double value) {
        this.recordId = recordId;
        this.typeId = typeId;
        this.groupId = groupId;
        this.date = date;
        this.value = value;
    }

    @Ignore
    public Record(long typeId, long groupId, Date date, double value) {
        this(0, typeId, groupId, date, value);
    }

    @Ignore
    public Record() {
        this(0, 0, 0, new Date(), 0.0);
    }
}
