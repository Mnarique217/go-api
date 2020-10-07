package cr.una.timetrackingapp.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name = "time_record" )
public class TimeRecord {
    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Integer mondayHours;

    @Getter
    @Setter
    private Integer tuesdayHours;

    @Getter
    @Setter
    private Integer wednesdayHours;

    @Getter
    @Setter
    private Integer thursdayHours;

    @Getter
    @Setter
    private Integer fridayHours;

    @Getter
    @Setter
    private Integer saturdayHours;

    @Getter
    @Setter
    private Integer sundayHours;

    @Column(name = "deparment_id")
    @Getter
    @Setter
    private Long departmentId;

    @Getter
    @Setter
    @Column(name = "timesheet_id")
    private Long timesheetId;
}
