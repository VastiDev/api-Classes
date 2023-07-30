package br.com.vastidev.api.resources;

import br.com.vastidev.api.domain.dto.UsersDto;
import br.com.vastidev.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping
    public ResponseEntity<List<UsersDto>> findAll(){
                return ResponseEntity.ok()
                        .body(service.findAll()
                                .stream().map(x -> mapper.map(x, UsersDto.class)).collect(Collectors.toList()));
    }
    @PostMapping
    public ResponseEntity<UsersDto> create(@RequestBody UsersDto obj){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(service.create(obj).getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
}
