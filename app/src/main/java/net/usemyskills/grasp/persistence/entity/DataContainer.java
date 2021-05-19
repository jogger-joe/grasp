package net.usemyskills.grasp.persistence.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import net.usemyskills.grasp.model.IDataContainer;

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
    public int getId() {
        return this.data.getDataId();
    }

    @Override
    public Date getDate() {
        return this.data.getDate();
    }

    @Override
    public String getDateString() {
        return new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN).format(this.getDate());
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public DataTypeTag getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeTag dataType) {
        this.dataType = dataType;
    }

    public DataTag getDataTag() {
        return dataTag;
    }

    public void setDataTag(DataTag dataTag) {
        this.dataTag = dataTag;
    }

    @Override
    public double getValue() {
        return (double)this.data.getValue() / (this.dataType == null ? 1 : this.dataType.getModifier());
    }

    @Override
    public DataTypeTag getTypeTag() {
        return this.dataType;
    }

    @Override
    public String getTypeName() {
        return this.getTypeTag() == null ? "" : this.getTypeTag().getName();
    }

    @Override
    public DataTag getTag() {
        return this.dataTag;
    }

    @Override
    public String getTagName() {
        return this.getTag() == null ? "" : this.getTag().getName();
    }
}
