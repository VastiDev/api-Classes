package br.com.vastidev.api.services.impl;


import br.com.vastidev.api.domain.Users;
import br.com.vastidev.api.repositories.UserRepository;
import br.com.vastidev.api.services.UserService;
import br.com.vastidev.api.services.exceptios.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(() ->new ObjectNotFoundException("Objeto não encontrado"));
    }
}
