package cr.una.timetrackingapp.backend.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * DTO for the entity User
 */
@Data
public class UserDto {
    private Long idUser;
    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private String email;
    private Date createDate;
    private boolean enabled;
    private boolean tokenExpired;
    private DepartmentDto department;
    private List<RoleDto> roleList;
}