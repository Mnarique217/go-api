package cr.una.timetrackingapp.backend.service;

import cr.una.timetrackingapp.backend.dao.ITimeRecordDao;
import cr.una.timetrackingapp.backend.model.TimeRecord;
import cr.una.timetrackingapp.backend.exception.TimeRecordNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
@RunWith(SpringRunner.class)
@SpringBootTest
class TimeRecordServiceTest {

    @Mock
    private ITimeRecordDao mockDao;

    @InjectMocks
    private TimeRecordService timeRecordServiceUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testFindById() throws TimeRecordNotFoundException {
        // Setup

        // Configure ITimeRecordDao.findById(...).
        final TimeRecord timeRecord = new TimeRecord();
        timeRecord.setId(0L);
        timeRecord.setMondayHours(0);
        timeRecord.setTuesdayHours(0);
        timeRecord.setWednesdayHours(0);
        timeRecord.setThursdayHours(0);
        timeRecord.setFridayHours(0);
        timeRecord.setSaturdayHours(0);
        timeRecord.setSundayHours(0);
        when(mockDao.findById(0L)).thenReturn(timeRecord);

        // Run the test
        final TimeRecord result = timeRecordServiceUnderTest.findById(0L);

        // Verify the results
        assertEquals(result.getId().longValue(), 0L);
    }

    @Test
    void testFindAll() {
        // Setup

        // Configure ITimeRecordDao.findAll(...).
        final TimeRecord timeRecord = new TimeRecord();
        timeRecord.setId(0L);
        timeRecord.setMondayHours(0);
        timeRecord.setTuesdayHours(0);
        timeRecord.setWednesdayHours(0);
        timeRecord.setThursdayHours(0);
        timeRecord.setFridayHours(0);
        timeRecord.setSaturdayHours(0);
        timeRecord.setSundayHours(0);
        final List<TimeRecord> timeRecords = Arrays.asList(timeRecord);
        when(mockDao.findAll()).thenReturn(timeRecords);

        // Run the test
        final List<TimeRecord> result = timeRecordServiceUnderTest.findAll();

        // Verify the results
        assertEquals(1, result.size());
    }

    @Test
    void testCreate() {
        // Setup
        final TimeRecord timeRecord = new TimeRecord();
        timeRecord.setId(0L);
        timeRecord.setMondayHours(0);
        timeRecord.setTuesdayHours(0);
        timeRecord.setWednesdayHours(0);
        timeRecord.setThursdayHours(0);
        timeRecord.setFridayHours(0);
        timeRecord.setSaturdayHours(0);
        timeRecord.setSundayHours(0);

        // Configure ITimeRecordDao.create(...).
        final TimeRecord timeRecord1 = new TimeRecord();
        timeRecord1.setId(0L);
        timeRecord1.setMondayHours(0);
        timeRecord1.setTuesdayHours(0);
        timeRecord1.setWednesdayHours(0);
        timeRecord1.setThursdayHours(0);
        timeRecord1.setFridayHours(0);
        timeRecord1.setSaturdayHours(0);
        timeRecord1.setSundayHours(0);
        when(mockDao.create(any(TimeRecord.class))).thenReturn(timeRecord1);

        // Run the test
        final TimeRecord result = timeRecordServiceUnderTest.create(timeRecord);

        // Verify the results
        assertNotNull(result);
    }

    @Test
    void testUpdate() {
        // Setup
        final TimeRecord timeRecord = new TimeRecord();
        timeRecord.setId(0L);
        timeRecord.setMondayHours(0);
        timeRecord.setTuesdayHours(0);
        timeRecord.setWednesdayHours(0);
        timeRecord.setThursdayHours(0);
        timeRecord.setFridayHours(0);
        timeRecord.setSaturdayHours(0);
        timeRecord.setSundayHours(0);

        // Configure ITimeRecordDao.update(...).
        final TimeRecord timeRecord1 = new TimeRecord();
        timeRecord1.setId(0L);
        timeRecord1.setMondayHours(1);
        timeRecord1.setTuesdayHours(0);
        timeRecord1.setWednesdayHours(0);
        timeRecord1.setThursdayHours(0);
        timeRecord1.setFridayHours(0);
        timeRecord1.setSaturdayHours(0);
        timeRecord1.setSundayHours(0);
        when(mockDao.update(any(TimeRecord.class))).thenReturn(timeRecord1);

        // Run the test
        final TimeRecord result = timeRecordServiceUnderTest.update(timeRecord);

        // Verify the results
        assertEquals(result.getMondayHours().longValue(), 1);
    }

    @Test
    void testDelete() {
        // Setup
        final TimeRecord timeRecord = new TimeRecord();
        timeRecord.setId(0L);
        timeRecord.setMondayHours(0);
        timeRecord.setTuesdayHours(0);
        timeRecord.setWednesdayHours(0);
        timeRecord.setThursdayHours(0);
        timeRecord.setFridayHours(0);
        timeRecord.setSaturdayHours(0);
        timeRecord.setSundayHours(0);

        // Run the test
        timeRecordServiceUnderTest.delete(timeRecord);

        // Verify the results
        verify(mockDao).delete(any(TimeRecord.class));
    }

    @Test
    void testDeleteById() throws TimeRecordNotFoundException {
        // Setup

        // Run the test
        timeRecordServiceUnderTest.deleteById(0L);

        // Verify the results
        verify(mockDao).deleteById(0L);
    }
}
