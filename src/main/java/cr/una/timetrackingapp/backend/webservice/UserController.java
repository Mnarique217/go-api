package cr.una.timetrackingapp.backend.webservice;

import cr.una.timetrackingapp.backend.dto.UserDto;
import cr.una.timetrackingapp.backend.model.User;
import cr.una.timetrackingapp.backend.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RequestMapping(value = "/api/v1/users")
public class UserController {

    @Autowired
    private IUserService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Save the new User
     * @param userDto the User saved
     * @return
     */
    @PostMapping(path = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserDto signUp(@Valid @RequestBody UserDto userDto) {
        User user = convertToEntity(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return convertToDto(service.create(user));
    }

    /**
     * Find all Priorities
     * @return List of DTO Priorities
     */
    @GetMapping()
    @ResponseBody
    public List<UserDto> findAll() {
        List<User> roleList = service.findAll();
        return roleList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * Find the user by id
     * Endpoint: /api/v1/users/1
     * @return A user found
     */
    @GetMapping("{id}")
    @ResponseBody
    public UserDto findById(@PathVariable Long id) {
        User user = service.findById(id);
        return convertToDto(user);
    }

    /**
     * Save the new Priority
     * @param userDto the priority saved
     * @return
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserDto save(@Valid @RequestBody UserDto userDto) {
        User user = convertToEntity(userDto);
        return convertToDto(service.create(user));
    }

    /**
     * Update the existing Priority
     * @param userDto the priority saved
     * @return the priority updated
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserDto update(@Valid @RequestBody UserDto userDto) {
        User user = convertToEntity(userDto);
        return convertToDto(service.update(user));
    }

    /**
     * Delete user by id
     * @param id the id of the entity
     */
    @DeleteMapping("{id}")
    @ResponseBody
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    /**
     * Convert from Entity to DTO
     * @param user entity
     * @return the DTO
     */
    private UserDto convertToDto(User user) {
        UserDto priorityDto = modelMapper.map(user, UserDto.class);
        return priorityDto;
    }

    /**
     * Convert from DTO to Entity
     * @param userDto the DTO
     * @return the entity
     */
    private User convertToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }
}