package br.com.vastidev.api.resources;

import br.com.vastidev.api.domain.Users;
import br.com.vastidev.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/users")
public class UserResource {

    private static final String ID = "/{id}";

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }
}
