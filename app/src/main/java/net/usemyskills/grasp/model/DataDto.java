package net.usemyskills.grasp.model;

import android.os.Parcel;
import android.os.Parcelable;

import net.usemyskills.grasp.persistence.entity.Data;

import java.util.Date;

public class DataDto extends Data implements Parcelable {

    public static final Creator<DataDto> CREATOR = new Creator<DataDto>() {
        @Override
        public DataDto createFromParcel(Parcel in) {
            int id = in.readInt();
            int typeId = in.readInt();
            int tagId = in.readInt();
            int value = in.readInt();
            long dateTimestamp = in.readLong();
            return new DataDto(id, typeId, tagId, new Date(dateTimestamp), value);
        }

        @Override
        public DataDto[] newArray(int size) {
            return new DataDto[size];
        }
    };

    public DataDto(int id, int typeId, int tagId, Date date, int value) {
        super(id, typeId, tagId, date, value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.getId());
        dest.writeInt(this.getTypeId());
        dest.writeInt(this.getTagId());
        dest.writeInt(this.getValue());
        dest.writeLong(this.getDate().getTime());
    }

    public void validate() throws Exception {
        if (this.getTypeId() == 0 || this.getDate() == null || this.getValue() == 0) {
            throw new Exception("validation error");
        }
    }
}
