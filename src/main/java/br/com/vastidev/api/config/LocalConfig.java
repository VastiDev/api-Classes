package br.com.vastidev.api.config;


import br.com.vastidev.api.domain.Users;
import br.com.vastidev.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class LocalConfig {

    private final UserRepository repository;

    @Autowired
    public LocalConfig(UserRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void initializeDB() {
        Users u1 = new Users(null, "Valdir", "valdir@mail.com", "123");
        Users u2 = new Users(null, "Luiz", "luiz@mail.com", "123");

        repository.saveAll(List.of(u1, u2));
    }
}
