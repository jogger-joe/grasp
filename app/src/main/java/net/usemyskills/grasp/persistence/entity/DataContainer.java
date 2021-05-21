package net.usemyskills.grasp.persistence.entity;

import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataContainer {
    @Embedded
    private Data data;

    @Relation(
            parentColumn = "typeId",
            entityColumn = "id"
    )
    private Type type;

    @Relation(
            parentColumn = "tagId",
            entityColumn = "id"
    )
    private Tag tag;

    public DataContainer(Data data, Type type, Tag tag) {
        this.data = data;
        this.type = type;
        this.tag = tag;
    }

    @Ignore
    public long getId() {
        return this.data.getId();
    }

    @Ignore
    public Date getDate() {
        return this.data.getDate();
    }

    @Ignore
    public String getDateString() {
        return new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN).format(this.getDate());
    }

    @Ignore
    public long getTimestamp() {
        return this.getDate().getTime();
    }

    @Ignore
    public Data getData() {
        return data;
    }

    @Ignore
    public void setData(Data data) {
        this.data = data;
    }

    @Ignore
    public Type getType() {
        return type;
    }

    @Ignore
    public void setType(Type type) {
        this.type = type;
    }

    @Ignore
    public Tag getTag() {
        return tag;
    }

    @Ignore
    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Ignore
    public double getValue() {
        return this.data.getValue();
    }

    @Ignore
    public String getValueLabel() {
        DecimalFormat decimalFormat = new DecimalFormat("###.##");
        StringBuilder stringBuilder = new StringBuilder(decimalFormat.format(this.getValue()));
        if (this.getTypeTag() != null) {
            stringBuilder.append(" ").append(this.getTypeTag().getUnit());
        }
        return stringBuilder.toString();
    }

    @Ignore
    public Type getTypeTag() {
        return this.type;
    }

    @Ignore
    public String getTypeName() {
        return this.getTypeTag() == null ? "" : this.getTypeTag().getName();
    }

    @Ignore
    public String getTagsLabel() {
        StringBuilder stringBuilder = new StringBuilder(this.getTypeName());
        if (this.getTagName() != null) {
            stringBuilder.append("(").append(this.getTagName()).append(")");
        }
        return stringBuilder.toString();
    }

    @Ignore
    public String getTagName() {
        return this.getTag() == null ? "" : this.getTag().getName();
    }
}
