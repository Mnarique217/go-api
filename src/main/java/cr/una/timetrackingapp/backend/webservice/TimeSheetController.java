package cr.una.timetrackingapp.backend.webservice;

import cr.una.timetrackingapp.backend.dto.TimeSheetDto;
import cr.una.timetrackingapp.backend.model.TimeSheet;
import cr.una.timetrackingapp.backend.service.ITimeSheetService;
import cr.una.timetrackingapp.backend.exception.TimeSheetNotFoundException;
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
 * Webservice Controller for TimeSheet
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/api/v1/timeSheets")
public class TimeSheetController {

    @Autowired
    private ITimeSheetService service;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Find all TimeSheets
     *
     * @return List of DTO TimeSheets
     */
    @GetMapping()
    @ResponseBody
    public List<TimeSheetDto> findAll() {
        List<TimeSheet> timeSheetList = service.findAll();
        return timeSheetList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * Find the timeSheet by id
     * Endpoint: /api/v1/timeSheets/1
     *
     * @return A timeSheet found
     */
    @GetMapping("{id}")
    @ResponseBody
    public TimeSheetDto findById(@PathVariable Long id) throws TimeSheetNotFoundException {
        TimeSheet timeSheet = service.findById(id);
        return convertToDto(timeSheet);
    }

    /**
     * Save the new TimeSheet
     *
     * @param timeSheetDto the timeSheet saved
     * @return a new timeSheet
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TimeSheetDto save(@Valid @RequestBody TimeSheetDto timeSheetDto) {
        TimeSheet timeSheet = convertToEntity(timeSheetDto);
        return convertToDto(service.create(timeSheet));
    }

    /**
     * Update the existing TimeSheet
     *
     * @param timeSheetDto the timeSheet saved
     * @return the timeSheet updated
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TimeSheetDto update(@Valid @RequestBody TimeSheetDto timeSheetDto) {
        TimeSheet timeSheet = convertToEntity(timeSheetDto);
        return convertToDto(service.update(timeSheet));
    }

    /**
     * Delete timeSheet by id
     *
     * @param id the id of the entity
     */
    @DeleteMapping("{id}")
    @ResponseBody
    public void deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
        } catch (TimeSheetNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "Priority Not Found", ex);
        }
    }

    /**
     * Convert from Entity to DTO
     *
     * @param timeSheet entity
     * @return the DTO
     */
    private TimeSheetDto convertToDto(TimeSheet timeSheet) {
        TimeSheetDto timeSheetDto = modelMapper.map(timeSheet, TimeSheetDto.class);
        return timeSheetDto;
    }

    /**
     * Convert from DTO to Entity
     *
     * @param timeSheetDto the DTO
     * @return the entity
     */
    private TimeSheet convertToEntity(TimeSheetDto timeSheetDto) {
        TimeSheet timeSheet = modelMapper.map(timeSheetDto, TimeSheet.class);
        return timeSheet;
    }
}
