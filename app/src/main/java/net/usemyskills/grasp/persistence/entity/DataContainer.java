package net.usemyskills.grasp.persistence.entity;

import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import net.usemyskills.grasp.model.IDataContainer;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataContainer implements IDataContainer {
    @Embedded
    private Data data;

    @Relation(
            parentColumn = "dataTypeId",
            entityColumn = "tagId"
    )
    private DataTypeTag dataType;

    @Relation(
            parentColumn = "dataTagId",
            entityColumn = "tagId"
    )
    private DataTag dataTag;

    public DataContainer(Data data, DataTypeTag dataType, DataTag dataTag) {
        this.data = data;
        this.dataType = dataType;
        this.dataTag = dataTag;
    }

    @Override
    @Ignore
    public int getId() {
        return this.data.getDataId();
    }

    @Override
    @Ignore
    public Date getDate() {
        return this.data.getDate();
    }

    @Override
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
    public DataTypeTag getDataType() {
        return dataType;
    }

    @Ignore
    public void setDataType(DataTypeTag dataType) {
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
    @Override
    public double getValue() {
        return this.data.getValue();
    }

    @Ignore
    @Override
    public String getValueLabel() {
        DecimalFormat decimalFormat = new DecimalFormat("###.##");
        StringBuilder stringBuilder = new StringBuilder(decimalFormat.format(this.getValue()));
        if (this.getTypeTag() != null) {
            stringBuilder.append(" ").append(this.getTypeTag().getUnit());
        }
        return stringBuilder.toString();
    }

    @Ignore
    @Override
    public DataTypeTag getTypeTag() {
        return this.dataType;
    }

    @Ignore
    @Override
    public String getTypeName() {
        return this.getTypeTag() == null ? "" : this.getTypeTag().getName();
    }

    @Ignore
    @Override
    public String getTagsLabel() {
        StringBuilder stringBuilder = new StringBuilder(this.getTypeName());
        if (this.getTagName() != null) {
            stringBuilder.append("(").append(this.getTagName()).append(")");
        }
        return stringBuilder.toString();
    }

    @Ignore
    @Override
    public DataTag getTag() {
        return this.dataTag;
    }

    @Ignore
    @Override
    public String getTagName() {
        return this.getTag() == null ? "" : this.getTag().getName();
    }
}
