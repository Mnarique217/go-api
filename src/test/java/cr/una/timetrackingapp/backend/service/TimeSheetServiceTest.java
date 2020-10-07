package cr.una.timetrackingapp.backend.service;

import cr.una.timetrackingapp.backend.dao.ITimeSheetDao;
import cr.una.timetrackingapp.backend.model.TimeSheet;
import cr.una.timetrackingapp.backend.exception.TimeSheetNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest

class TimeSheetServiceTest {

    @Mock
    private ITimeSheetDao mockDao;

    @InjectMocks
    private TimeSheetService timeSheetServiceUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testFindById() throws TimeSheetNotFoundException {
        // Setup
        final TimeSheet timeSheet = new TimeSheet();
        timeSheet.setId(5L);
        timeSheet.setName("Creating TimeSheet Test");
        when(mockDao.findById(5L)).thenReturn(timeSheet);

        // Run the test
        final TimeSheet result = timeSheetServiceUnderTest.findById(5L);

        // Verify the results
        assertEquals(5L, result.getId().longValue());
    }

    @Test
    void testFindAll() {
        // Setup
        when(mockDao.findAll()).thenReturn(Collections.singletonList(new TimeSheet()));

        // Run the test
        final List<TimeSheet> result = timeSheetServiceUnderTest.findAll();

        // Verify the results
        assertEquals(1, result.size());
    }

    @Test
    void testCreate() {
        // Setup
        final TimeSheet timeSheet = new TimeSheet();
        timeSheet.setId(5L);
        timeSheet.setName("Creating TimeSheet Test");
        when(mockDao.create(any(TimeSheet.class))).thenReturn(timeSheet);

        // Run the test
        final TimeSheet result = timeSheetServiceUnderTest.create(timeSheet);

        // Verify the results
        assertEquals(5L, result.getId().longValue());
    }

    @Test
    void testUpdate() {
        // Setup
        final TimeSheet timeSheet = new TimeSheet();
        timeSheet.setId(5L);
        timeSheet.setName("Creating TimeSheet Test");
        when(mockDao.update(any(TimeSheet.class))).thenReturn(timeSheet);

        // Run the test
        final TimeSheet result = timeSheetServiceUnderTest.update(timeSheet);

        // Verify the results
        assertEquals(5L, result.getId().longValue());
    }

    @Test
    void testDelete() {
        // Setup
        final TimeSheet timeSheet = new TimeSheet();

        // Run the test
        timeSheetServiceUnderTest.delete(timeSheet);

        // Verify the results
        verify(mockDao).delete(any(TimeSheet.class));
    }

    @Test
    void testDeleteById() throws TimeSheetNotFoundException {
        // Setup

        // Run the test
        timeSheetServiceUnderTest.deleteById(0L);

        // Verify the results
        verify(mockDao).deleteById(0L);
    }
}
