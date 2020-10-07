
package cr.una.timetrackingapp.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Entity for timesheet
 */
@Entity
@Table(name = "time_sheet")
public class TimeSheet {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

   //  Relationship definition
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "timesheet_id", updatable = false)
    @Getter
    @Setter
    private List<TimeRecord> timeRecordList;
}
