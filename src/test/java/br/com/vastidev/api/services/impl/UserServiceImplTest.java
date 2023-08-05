package br.com.vastidev.api.services.impl;

import br.com.vastidev.api.domain.Users;
import br.com.vastidev.api.domain.dto.UsersDto;
import br.com.vastidev.api.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME1 = "Valdir";
    public static final String MAIL = "valdir@mail.com";
    public static final String PASSWORD = "123";

    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;

    private Users users;

    private UsersDto usersDto;

    private Optional<Users> optionalUsers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUsers);
        Users response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
    private void startUser(){
        users = new Users(ID, NAME1, MAIL, PASSWORD);
        usersDto = new UsersDto(ID, NAME1, MAIL, PASSWORD);
        optionalUsers = Optional.of(new Users(ID, NAME1, MAIL, PASSWORD));

    }

}