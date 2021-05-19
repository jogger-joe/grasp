package net.usemyskills.grasp.model;


import net.usemyskills.grasp.persistence.entity.DataTag;
import net.usemyskills.grasp.persistence.entity.DataTypeTag;

import java.util.Date;

public interface IDataContainer {
    int getId();
    Date getDate();
    String getDateString();
    double getValue();
    DataTypeTag getTypeTag();
    String getTypeName();
    DataTag getTag();
    String getTagName();
}
