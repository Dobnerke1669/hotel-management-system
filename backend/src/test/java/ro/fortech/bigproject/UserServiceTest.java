package ro.fortech.bigproject;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ro.fortech.project.entities.ERole;
import ro.fortech.project.entities.Role;
import ro.fortech.project.entities.User;
import ro.fortech.project.repository.RoleRepository;
import ro.fortech.project.repository.UserRepository;
import ro.fortech.project.services.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private UserService userService;



    @Test
    public void checkUsernameTest(){
        String username = "testuser";
        userService.checkUsername(username);
        verify(userRepository, times(1)).existsByUsername(username);
    }

    @Test
    public void checkEmailTest(){
        String email = "testuser@yahoo.com";
        userService.checkEmail(email);
        verify(userRepository, times(1)).existsByEmail(email);
    }


    @Test
    public void createUserTest() throws Exception {
        User user = new User("test","test@yahoo.com","$2a$12$FzmZcBGxfqwPj0lFBFP8aeTk9J9zGJe9MfUHlmXG5Py05oyGGEgAi","Oana","Crisan",true);
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.createUser(user);
        assertEquals(user,savedUser);
        verify(userRepository, times(1)).save(savedUser);
    }

    @Test
    public void testCreateRolesWithAdminRole() {

        when(roleRepository.findByName(ERole.ROLE_ADMIN)).thenReturn(Optional.of(new Role(ERole.ROLE_ADMIN)));
        List<String> strRoles = new ArrayList<>();
        strRoles.add("admin");
        List<Role> roles = userService.createRoles(strRoles);
        assertEquals(1, roles.size());
        assertEquals(ERole.ROLE_ADMIN, roles.get(0).getName());
        verify(roleRepository,times(1)).findByName(ERole.ROLE_ADMIN);
    }

    @Test
    public void testInsertUserRole() {
        when(roleRepository.findByName(ERole.ROLE_CLIENT)).thenReturn(Optional.of(new Role(ERole.ROLE_CLIENT)));
        List<Role> roles = userService.insertUserRole();
        assertEquals(1, roles.size());
        assertEquals(ERole.ROLE_CLIENT, roles.get(0).getName());
        verify(roleRepository, times(1)).findByName(ERole.ROLE_CLIENT);
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User mockUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        User user = userService.getUserById(userId);
        assertEquals(mockUser, user);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testFindAllUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User());
        mockUsers.add(new User());
        when(userRepository.findAll()).thenReturn(mockUsers);
        List<User> users = userService.findAllUsers();
        assertEquals(mockUsers.size(), users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testFindByFirstNameAndLastName() {
        String firstName = "John";
        String lastName = "Doe";
        User mockUser = new User();
        mockUser.setFirstName(firstName);
        mockUser.setLastName(lastName);
        when(userRepository.findByFirstName(firstName)).thenReturn(Collections.singletonList(mockUser));
        User user = userService.findByFirstNameAndLastName(firstName, lastName);
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        verify(userRepository, times(1)).findByFirstName(firstName);
    }

}
