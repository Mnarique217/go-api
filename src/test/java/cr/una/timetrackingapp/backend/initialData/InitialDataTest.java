/*
 *
 *  *
 *  *
 *  * Universidad Nacional de Costa Rica  2020
 *  *
 *  * mike@guzmanalan.com
 *  *
 *  *
 *
 *
 */

package cr.una.timetrackingapp.backend.initialData;


import cr.una.timetrackingapp.backend.model.User;
import cr.una.timetrackingapp.backend.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class to import initial data to database
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Sql({
        "/initial-data.sql",
})
public class InitialDataTest {

    @Autowired
    private IUserService userService;

    @Test
    public void loadPriorities() {
        List<User> userList = userService.findAll();

        assertTrue(userList.size() == 0);
    }
}
