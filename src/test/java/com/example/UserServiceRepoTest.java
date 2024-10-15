package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceRepoTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private UserServiceRepo userServiceRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(userServiceRepo, "allowUserUpdate", true);
    }

    @Test
    void saveUser_success() {
        UserBase user = new Client("John");
        userServiceRepo.saveUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void saveUser_failure() {
        doThrow(new RuntimeException("Error saving user")).when(userRepository).save(any(UserBase.class));
        assertThrows(RuntimeException.class, () -> {
            userServiceRepo.saveUser(new Client("John"));
        });
    }

    @Test
    void saveUser_withNullName() {
        UserBase user = new Client(null);
        userServiceRepo.saveUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void addTicketToUser_success() {
        UserBase user = new Client("John");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Ticket ticket = new Ticket();
        userServiceRepo.addTicketToUser(1, ticket);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void addTicketToUser_userNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        Ticket ticket = new Ticket();
        userServiceRepo.addTicketToUser(1, ticket);

        verify(userRepository, never()).save(any(UserBase.class));
    }

    @Test
    void addTicketToUser_withNullTicket() {
        UserBase user = new Client("John");
        when(userRepository.findById(25)).thenReturn(Optional.of(user));
        Ticket ticket = null;
        userServiceRepo.addTicketToUser(25, ticket);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void fetchUserById_success() {
        UserBase user = new Client("John");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        UserBase result = userServiceRepo.fetchUserById(1);
        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void fetchUserById_userNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userServiceRepo.fetchUserById(1);
        });
        assertEquals("User with ID 1 not found.", exception.getMessage());
    }

    @Test
    void fetchUserById_withInvalidId() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userServiceRepo.fetchUserById(-1);
        });
        assertEquals("User with ID -1 not found.", exception.getMessage());
    }

    @Test
    void deleteUserById_success() {
        userServiceRepo.deleteUserById(1);
        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteUserById_failure() {
        doThrow(new RuntimeException("Error deleting user")).when(userRepository).deleteById(1);
        assertThrows(RuntimeException.class, () -> userServiceRepo.deleteUserById(1));
    }

    @Test
    void deleteUserById_nonExistentUser() {
        // Deleting non-existing user
        doNothing().when(userRepository).deleteById(100);
        userServiceRepo.deleteUserById(100);
        verify(userRepository, times(1)).deleteById(100);
    }

    @Test
    void fetchAllUsers_success() {
        when(userRepository.findAll()).thenReturn(List.of(new Client("John"), new Admin("Admin")));

        List<UserBase> users = userServiceRepo.fetchAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    void fetchAllUsers_emptyList() {
        when(userRepository.findAll()).thenReturn(List.of());
        List<UserBase> users = userServiceRepo.fetchAllUsers();
        assertTrue(users.isEmpty());
    }

    @Test
    void fetchAllUsers_nullCase() {
        when(userRepository.findAll()).thenReturn(null);
        List<UserBase> users = userServiceRepo.fetchAllUsers();
        assertNull(users);
    }

    @Test
    void updateUser_success() {
        when(userRepository.findById(1)).thenReturn(Optional.of(new Client("John")));
        when(userRepository.save(any(UserBase.class))).thenReturn(null);

        userServiceRepo.updateUser(1, new Client("Updated Name"));
        verify(userRepository, times(1)).save(any(UserBase.class));
    }

    @Test
    void updateUser_updateDisabled() {
        ReflectionTestUtils.setField(userServiceRepo, "allowUserUpdate", false);
        assertThrows(UnsupportedOperationException.class, () -> {
            userServiceRepo.updateUser(25, new Client("Updated Name"));
        });

    }

    @Test
    void updateUser_withNullName() {
        when(userRepository.findById(1)).thenReturn(Optional.of(new Client("John")));
        userServiceRepo.updateUser(1, new Client(null));
        verify(userRepository, times(1)).save(any(UserBase.class));
    }


}
