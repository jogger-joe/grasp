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
            parentColumn = "dataTypeId",
            entityColumn = "id"
    )
    private DataType dataType;

    @Relation(
            parentColumn = "dataTagId",
            entityColumn = "id"
    )
    private DataTag dataTag;

    public DataContainer(Data data, DataType dataType, DataTag dataTag) {
        this.data = data;
        this.dataType = dataType;
        this.dataTag = dataTag;
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
    public Data getData() {
        return data;
    }

    @Ignore
    public void setData(Data data) {
        this.data = data;
    }

    @Ignore
    public DataType getDataType() {
        return dataType;
    }

    @Ignore
    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    @Ignore
    public DataTag getDataTag() {
        return dataTag;
    }

    @Ignore
    public void setDataTag(DataTag dataTag) {
        this.dataTag = dataTag;
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
    public DataType getTypeTag() {
        return this.dataType;
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
    public DataTag getTag() {
        return this.dataTag;
    }

    @Ignore
    public String getTagName() {
        return this.getTag() == null ? "" : this.getTag().getName();
    }
}
