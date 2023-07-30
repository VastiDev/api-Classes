package br.com.vastidev.api.services;

import br.com.vastidev.api.domain.Users;

import java.util.List;

public interface UserService {
    Users findById(Integer id);

    List<Users> findAll();
}
