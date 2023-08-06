package br.com.vastidev.api.services.impl;

import br.com.vastidev.api.domain.Users;
import br.com.vastidev.api.domain.dto.UsersDto;
import br.com.vastidev.api.repositories.UserRepository;
import br.com.vastidev.api.services.exceptios.DataIntegratyViolationException;
import br.com.vastidev.api.services.exceptios.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME1 = "Valdir";
    public static final String MAIL = "valdir@mail.com";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;

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
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        try{
            service.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }
     @Test
     void whenFindAllThenReturnList() {
        when(repository.findAll()).thenReturn(List.of(users));

         List<Users> response = service.findAll();

         assertNotNull(response);
         assertEquals(1, response.size());
         assertEquals(Users.class, response.get(INDEX).getClass());

         assertEquals(ID, response.get(INDEX).getId());
         assertEquals(NAME1, response.get(INDEX).getName());
         assertEquals(MAIL, response.get(INDEX).getEmail());
         assertEquals(PASSWORD, response.get(INDEX).getPassword());
     }

    @Test
    void whenCreateThenReturnSucces() {
        when(repository.save(any())).thenReturn(users);

        Users response = service.create(usersDto);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME1, response.getName());
        assertEquals(MAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }
    @Test
    void whenCreateThenReturnIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUsers);
        try{
            optionalUsers.get().setId(2);
            service.create(usersDto);
        }catch(Exception ex){

            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrasdo no sistema!", ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(users);

        Users response = service.update(usersDto);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME1, response.getName());
        assertEquals(MAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }
    @Test
    void whenUpdateThenReturnIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUsers);
        try{
            optionalUsers.get().setId(2);
            service.update(usersDto);
        }catch(Exception ex){

            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrasdo no sistema!", ex.getMessage());
        }
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