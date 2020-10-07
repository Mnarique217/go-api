package cr.una.timetrackingapp.backend.dao;

import cr.una.timetrackingapp.backend.model.TimeSheet;
import cr.una.timetrackingapp.backend.exception.TimeSheetNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
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
class TimeSheetDaoTest {

    @Autowired
    private ITimeSheetDao timeSheetDaoUnderTest;

    @Test
    void testFindById() throws TimeSheetNotFoundException {
        // Setup

        // Run the test
        final TimeSheet result = timeSheetDaoUnderTest.findById(1L);

        // Verify the results
        assertEquals(result.getId().intValue(), 1L);
    }

    @Test
    void testFindAll() {
        // Setup

        // Run the test
        final List<TimeSheet> result = timeSheetDaoUnderTest.findAll();

        // Verify the results
        assertEquals(5, result.size());
    }

    @Test
    void testCreate() {
        // Setup
        final TimeSheet timeSheet = new TimeSheet();
        timeSheet.setId(5L);
        timeSheet.setName("Creating TimeSheet Test");

        // Run the test
        final TimeSheet result = timeSheetDaoUnderTest.create(timeSheet);

        // Verify the results
        assertEquals(result.getName(), "Creating TimeSheet Test");
    }

    @Test
    void testUpdate() {
        // Setup
        final TimeSheet timeSheet = new TimeSheet();
        timeSheet.setId(7L);
        timeSheet.setName("Updating TimeSheet Test");

        // Run the test
        final TimeSheet result = timeSheetDaoUnderTest.update(timeSheet);

        // Verify the results
        assertEquals(result.getName(), "Updating TimeSheet Test");
    }

    @Test
    void testDelete() throws TimeSheetNotFoundException {
        // Setup
        final TimeSheet timeSheet = new TimeSheet();
        timeSheet.setId(3L);
        timeSheet.setName("Updating TimeSheet Test");
        // Run the test
        timeSheetDaoUnderTest.delete(timeSheet);

        // Verify the results
        assertNull(timeSheetDaoUnderTest.findById(3L));
    }

    @Test
    void testDeleteById() throws TimeSheetNotFoundException {
        // Setup

        // Run the test
        timeSheetDaoUnderTest.deleteById(1L);

        // Verify the results
        assertNull(timeSheetDaoUnderTest.findById(1L));
    }
}
