package br.com.vastidev.api.services.impl;


import br.com.vastidev.api.domain.Users;
import br.com.vastidev.api.domain.dto.UsersDto;
import br.com.vastidev.api.repositories.UserRepository;
import br.com.vastidev.api.services.UserService;
import br.com.vastidev.api.services.exceptios.DataIntegratyViolationException;
import br.com.vastidev.api.services.exceptios.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(() ->new ObjectNotFoundException("Objeto não encontrado"));
    }
    public List<Users> findAll(){
        return repository.findAll();
    }

    @Override
    public Users create(UsersDto obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Users.class));}

    private void findByEmail(UsersDto obj) {
        Optional<Users> users = repository.findByEmail(obj.getEmail());
        if(users.isPresent()){
            throw new DataIntegratyViolationException("E-mail já cadastrasdo no sistema!");
        }
        }

}
