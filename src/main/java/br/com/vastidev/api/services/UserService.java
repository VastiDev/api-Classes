package br.com.vastidev.api.services;

import br.com.vastidev.api.domain.Users;
import br.com.vastidev.api.domain.dto.UsersDto;

import java.util.List;

public interface UserService {
    Users findById(Integer id);

    List<Users> findAll();

    Users create(UsersDto obj);

    Users update(UsersDto obj);

    void delete(Integer id);
}

