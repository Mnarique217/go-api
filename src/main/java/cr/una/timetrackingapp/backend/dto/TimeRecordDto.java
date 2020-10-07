package cr.una.timetrackingapp.backend.dto;

import lombok.Data;

/**
 * DTO for the entity Department
 */
@Data
public class TimeRecordDto {

    private Long id;

    private Integer mondayHours;

    private Integer tuesdayHours;

    private Integer wednesdayHours;

    private Integer thursdayHours;

    private Integer fridayHours;

    private Integer saturdayHours;

    private Integer sundayHours;

    private Integer departmentId;

    private Integer timesheetId;
}
