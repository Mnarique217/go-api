package cr.una.timetrackingapp.backend.webservice;

import cr.una.timetrackingapp.backend.dto.TimeRecordDto;
import cr.una.timetrackingapp.backend.model.TimeRecord;
import cr.una.timetrackingapp.backend.service.ITimeRecordService;
import cr.una.timetrackingapp.backend.exception.TimeRecordNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Webservice Controller for TimeRecord
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/api/v1/timeRecords")
public class TimeRecordController {

    @Autowired
    private ITimeRecordService service;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Find all TimeRecords
     *
     * @return List of DTO TimeRecords
     */
    @GetMapping()
    @ResponseBody
    public List<TimeRecordDto> findAll() {
        List<TimeRecord> timeRecordList = service.findAll();
        return timeRecordList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * Find the timeRecord by id
     * Endpoint: /api/v1/timeRecords/1
     *
     * @return A timeRecord found
     */
    @GetMapping("{id}")
    @ResponseBody
    public TimeRecordDto findById(@PathVariable Long id) throws TimeRecordNotFoundException {
        TimeRecord timeRecord = service.findById(id);
        return convertToDto(timeRecord);
    }

    /**
     * Save the new TimeRecord
     *
     * @param timeRecordDto the timeRecord saved
     * @return a new timeRecord
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TimeRecordDto save(@Valid @RequestBody TimeRecordDto timeRecordDto) {
        TimeRecord timeRecord = convertToEntity(timeRecordDto);
        return convertToDto(service.create(timeRecord));
    }

    /**
     * Update the existing TimeRecord
     *
     * @param timeRecordDto the timeRecord saved
     * @return the timeRecord updated
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TimeRecordDto update(@Valid @RequestBody TimeRecordDto timeRecordDto) {
        TimeRecord timeRecord = convertToEntity(timeRecordDto);
        return convertToDto(service.update(timeRecord));
    }

    /**
     * Delete timeRecord by id
     *
     * @param id the id of the entity
     */
    @DeleteMapping("{id}")
    @ResponseBody
    public void deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
        } catch (TimeRecordNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "TimeRecord Not Found", ex);
        }
    }

    /**
     * Convert from Entity to DTO
     *
     * @param timeRecord entity
     * @return the DTO
     */
    private TimeRecordDto convertToDto(TimeRecord timeRecord) {
        TimeRecordDto timeRecordDto = modelMapper.map(timeRecord, TimeRecordDto.class);
        return timeRecordDto;
    }

    /**
     * Convert from DTO to Entity
     *
     * @param timeRecordDto the DTO
     * @return the entity
     */
    private TimeRecord convertToEntity(TimeRecordDto timeRecordDto) {
        TimeRecord timeRecord = modelMapper.map(timeRecordDto, TimeRecord.class);
        return timeRecord;
    }
}
