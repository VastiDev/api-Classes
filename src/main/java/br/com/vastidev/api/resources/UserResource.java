package br.com.vastidev.api.resources;

import br.com.vastidev.api.domain.dto.UsersDto;
import br.com.vastidev.api.services.UserService;
import org.modelmapper.ModelMapper;
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
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UsersDto.class));
    }
}
