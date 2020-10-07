package cr.una.timetrackingapp.backend.dao;

import cr.una.timetrackingapp.backend.model.TimeRecord;
import cr.una.timetrackingapp.backend.exception.TimeRecordNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@Sql({
        "/import-department.sql",
        "/import-roles.sql",
        "/import-privileges.sql",
        "/import-users.sql",
        "/import-user-role.sql",
        "/import-timesheet.sql",
        "/import-timerecord.sql"

})
class TimeRecordDaoTest {

    @Autowired
    private ITimeRecordDao timeRecordDaoUnderTest;

    @Test
    public void testFindById() throws TimeRecordNotFoundException {
        // Setup

        // Run the test
        final TimeRecord result = timeRecordDaoUnderTest.findById(1L);

        // Verify the results
        assertEquals(result.getId().intValue(), 1);
    }

    @Test
    public void testFindAll() {
        // Setup

        // Run the test
        final List<TimeRecord> result = timeRecordDaoUnderTest.findAll();

        // Verify the results
        assertEquals(5, result.size());
    }

    @Test
    public void testCreate() {
        // Setup
        final TimeRecord timeRecord = new TimeRecord();
        timeRecord.setId(5L);
        timeRecord.setMondayHours(0);
        timeRecord.setTuesdayHours(0);
        timeRecord.setWednesdayHours(0);
        timeRecord.setThursdayHours(0);
        timeRecord.setFridayHours(0);
        timeRecord.setSaturdayHours(0);
        timeRecord.setSundayHours(0);
        timeRecord.setDepartmentId(1L);

        // Run the test
        final TimeRecord result = timeRecordDaoUnderTest.create(timeRecord);

        // Verify the results
        assertEquals(timeRecord.getId(), result.getId());
        assertEquals(1L, timeRecord.getDepartmentId().longValue());
    }

    @Test
    public void testUpdate() {
        // Setup
        final TimeRecord timeRecord = new TimeRecord();
        timeRecord.setId(2L);
        timeRecord.setMondayHours(0);
        timeRecord.setTuesdayHours(0);
        timeRecord.setWednesdayHours(0);
        timeRecord.setThursdayHours(0);
        timeRecord.setFridayHours(0);
        timeRecord.setSaturdayHours(0);
        timeRecord.setSundayHours(0);
        timeRecord.setDepartmentId(1L);

        // Run the test
        final TimeRecord result = timeRecordDaoUnderTest.update(timeRecord);

        // Verify the results
        assertEquals(result.getMondayHours().intValue(), 0);
        assertEquals(result.getTuesdayHours().intValue(), 0);
        assertEquals(result.getWednesdayHours().intValue(), 0);
        assertEquals(1L, timeRecord.getDepartmentId().longValue());
    }

    @Test
    public void testDelete() throws TimeRecordNotFoundException {
        // Setup
        final TimeRecord timeRecord = new TimeRecord();
        timeRecord.setId(3L);

        // Run the test
        timeRecordDaoUnderTest.delete(timeRecord);

        // Verify the results
        assertNull(timeRecordDaoUnderTest.findById(3L));
    }

    @Test
    void testDeleteById() throws TimeRecordNotFoundException {
        // Setup

        // Run the test
        timeRecordDaoUnderTest.deleteById(4L);

        // Verify the results
        assertNull(timeRecordDaoUnderTest.findById(4L));
    }
}
