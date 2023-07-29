package br.com.vastidev.api.services;

import br.com.vastidev.api.domain.User;

public interface UserService {
    User findById(Integer id);

}
