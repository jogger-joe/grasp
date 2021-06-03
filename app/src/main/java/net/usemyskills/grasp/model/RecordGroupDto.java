package net.usemyskills.grasp.model;

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
        super("", 0);
    }
}
