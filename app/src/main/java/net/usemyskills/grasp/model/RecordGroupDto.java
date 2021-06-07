package net.usemyskills.grasp.model;

import net.usemyskills.grasp.R;

public class RecordGroupDto extends TagDto {
    public int iconId;

    public RecordGroupDto(RecordGroupDto recordGroupDto) {
        super(recordGroupDto);
        this.iconId = recordGroupDto.iconId;
    }

    public RecordGroupDto(String name, int iconId) {
        super(name, 0);
        this.iconId = iconId;
    }

    public RecordGroupDto() {
        this("", R.drawable.ic_default);
    }
}
