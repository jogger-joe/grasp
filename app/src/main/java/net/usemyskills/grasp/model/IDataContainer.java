package net.usemyskills.grasp.model;


import net.usemyskills.grasp.persistence.entity.DataTag;
import net.usemyskills.grasp.persistence.entity.DataType;

import java.util.Date;

public interface IDataContainer {
    long getId();
    Date getDate();
    String getDateString();
    double getValue();
    String getValueLabel();
    DataType getTypeTag();
    String getTypeName();
    DataTag getTag();
    String getTagName();
    String getTagsLabel();
}
