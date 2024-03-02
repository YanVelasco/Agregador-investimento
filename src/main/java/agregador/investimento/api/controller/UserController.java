package agregador.investimento.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import agregador.investimento.api.dto.CreateUserDTO;
import agregador.investimento.api.entity.UserEntity;
import agregador.investimento.api.service.CreateUserService;
import agregador.investimento.api.service.LisAllUsers;
import agregador.investimento.api.service.ListUserById;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private ListUserById listUserById;

    @Autowired
    private LisAllUsers lisAllUsers;
    
    @PostMapping()
    public ResponseEntity<UserEntity> createUser(@RequestBody CreateUserDTO createUserDTO, UriComponentsBuilder uriComponentsBuilder) {
        var createUser = createUserService.execute(createUserDTO);
        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(createUser.getId()).toUri();
        return ResponseEntity.created(uri).body(createUser);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("userId") UUID userId) {
        var user = listUserById.execute(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping()
    public ResponseEntity<List<UserEntity>> listUsers() {
        var users = lisAllUsers.execute();
        return ResponseEntity.ok(users);
    }
    

}
