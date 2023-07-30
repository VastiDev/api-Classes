package br.com.vastidev.api.services;

import br.com.vastidev.api.domain.Users;

public interface UserService {
    Users findById(Integer id);

}
