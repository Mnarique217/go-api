package cr.una.timetrackingapp.backend.dto;

import lombok.Data;

import java.util.List;

/**
 * DTO for the entity Department
 */
@Data
public class TimeSheetDto {

    private Long id;

    private String name;

    private List<TimeRecordDto> timeRecordList;
}
