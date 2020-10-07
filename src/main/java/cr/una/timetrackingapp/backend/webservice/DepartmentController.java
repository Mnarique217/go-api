package cr.una.timetrackingapp.backend.webservice;

import cr.una.timetrackingapp.backend.dto.DepartmentDto;
import cr.una.timetrackingapp.backend.model.Department;
import cr.una.timetrackingapp.backend.service.IDepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Webservice Controller for Department
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/api/v1/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService service;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Find all Departments
     *
     * @return List of DTO Departments
     */
    @GetMapping()
    @ResponseBody
    public List<DepartmentDto> findAll() {
        List<Department> departmentList = service.findAll();
        return departmentList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * Find the department by id
     * Endpoint: /api/v1/departments/1
     *
     * @return A department found
     */
    @GetMapping("{id}")
    @ResponseBody
    public DepartmentDto findById(@PathVariable Long id) {
        Department department = service.findById(id);
        return convertToDto(department);
    }

    /**
     * Save the new Department
     *
     * @param departmentDto the department saved
     * @return a new department
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DepartmentDto save(@Valid @RequestBody DepartmentDto departmentDto) {
        Department department = convertToEntity(departmentDto);
        return convertToDto(service.create(department));
    }

    /**
     * Update the existing Department
     *
     * @param departmentDto the department saved
     * @return the department updated
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DepartmentDto update(@Valid @RequestBody DepartmentDto departmentDto) {
        Department department = convertToEntity(departmentDto);
        return convertToDto(service.update(department));
    }

    /**
     * Delete department by id
     *
     * @param id the id of the entity
     */
    @DeleteMapping("{id}")
    @ResponseBody
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    /**
     * Convert from Entity to DTO
     *
     * @param department entity
     * @return the DTO
     */
    private DepartmentDto convertToDto(Department department) {
        DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
        return departmentDto;
    }

    /**
     * Convert from DTO to Entity
     *
     * @param departmentDto the DTO
     * @return the entity
     */
    private Department convertToEntity(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        return department;
    }
}
