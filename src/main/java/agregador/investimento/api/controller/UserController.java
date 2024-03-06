package agregador.investimento.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import agregador.investimento.api.dto.AccountResponseDTO;
import agregador.investimento.api.dto.CreateAccountDTO;
import agregador.investimento.api.dto.CreateUserDTO;
import agregador.investimento.api.dto.UpdateUserDTO;
import agregador.investimento.api.entity.UserEntity;
import agregador.investimento.api.service.CreateAccounte;
import agregador.investimento.api.service.CreateUser;
import agregador.investimento.api.service.DeleteUserById;
import agregador.investimento.api.service.ListAccounts;
import agregador.investimento.api.service.ListAllUsers;
import agregador.investimento.api.service.ListUserById;
import agregador.investimento.api.service.UpdateUserById;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private CreateUser createUserService;

    @Autowired
    private ListUserById listUserById;

    @Autowired
    private ListAllUsers lisAllUsers;

    @Autowired
    private UpdateUserById updateUserById;

    @Autowired
    private DeleteUserById deleteUserById;

    @Autowired
    private CreateAccounte createAccounte;


    @Autowired
    private ListAccounts listAccounts;

    @PostMapping()
    public ResponseEntity<UserEntity> createUser(@RequestBody CreateUserDTO createUserDTO,
            UriComponentsBuilder uriComponentsBuilder) {
        var createUser = createUserService.execute(createUserDTO);
        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(createUser.getId()).toUri();
        return ResponseEntity.created(uri).body(createUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("userId") UUID userId) {
        var user = listUserById.execute(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<UserEntity>> listUsers() {
        var users = lisAllUsers.execute();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserEntity> updateById(@PathVariable("userId") String userId,
            @RequestBody UpdateUserDTO updateUserDTO) {
        try {
            var updateUser = updateUserById.execute(userId, updateUserDTO);
            return ResponseEntity.ok().body(updateUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId) {
        deleteUserById.execute(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> createAccount(@RequestBody CreateAccountDTO createAccountDTO,
            @PathVariable("userId") String userId) {
        createAccounte.execute(userId, createAccountDTO);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDTO>> createAccount(@PathVariable("userId") String userId) {
        var accounts = listAccounts.execute(UUID.fromString(userId));
        return ResponseEntity.ok(accounts);
    }

}
