package com.example.demo.domain.user;

import com.example.demo.domain.mylistentry.MyListEntryService;
import com.example.demo.domain.mylistentry.dto.MyListEntryDTO;
import com.example.demo.domain.mylistentry.dto.MyListEntryMapper;
import com.example.demo.domain.user.dto.*;

import java.util.List;
import java.util.UUID;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  private final MyListEntryService myListEntryService;
  private final UserMapper userMapper;

  private final MyListEntryMapper myListEntryMapper;

  @Autowired
  public UserController(UserService userService, MyListEntryService myListEntryService, UserMapper userMapper, UserMinimalMapper userMinimalMapper, MyListEntryMapper myListEntryMapper) {
    this.userService = userService;
    this.myListEntryService = myListEntryService;
    this.userMapper = userMapper;
    this.myListEntryMapper = myListEntryMapper;
  }

  // Retrieve user by ID
  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> retrieveById(@PathVariable UUID id) {
    User user = userService.findById(id);
    return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
  }

  // Retrieve all MyListEntries of a user, sorted by importance
  @GetMapping("/{id}/getallmylistentriessorted")
  @PreAuthorize("hasAuthority('MYLISTENTRIES_OF_A_SPECIFIC_USER')")
  public ResponseEntity<List<MyListEntryDTO>> retrieveAllMyListEntireOfAUser(@PathVariable UUID id){
    return ResponseEntity.status(HttpStatus.OK).body(myListEntryMapper.toDTOs(myListEntryService.sortedMyListEntryListOfSpecificUserByImportance(id)));
  }

  // Retrieve all users
  @GetMapping({"", "/"})
  public ResponseEntity<List<UserDTO>> retrieveAll() {
    List<User> users = userService.findAll();
    return new ResponseEntity<>(userMapper.toDTOs(users), HttpStatus.OK);
  }

  // Register a new user
  @PostMapping("/register")
  public ResponseEntity<UserDTO> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
    User user = userService.register(userMapper.fromUserRegisterDTO(userRegisterDTO));
    return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.CREATED);
  }

  // Register a new user without password
  @PostMapping("/registerUser")
  public ResponseEntity<UserDTO> registerWithoutPassword(@Valid @RequestBody UserDTO userDTO) {
    User user = userService.registerUser(userMapper.fromDTO(userDTO));
    return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.CREATED);
  }

  // Update user by ID
  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('USER_MODIFY') && @userPermissionEvaluator.isUserAboveAge(authentication.principal.user,18)")
  public ResponseEntity<UserDTO> updateById(@PathVariable UUID id, @Valid @RequestBody UserDTO userDTO) {
    User user = userService.updateById(id, userMapper.fromDTO(userDTO));
    return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
  }

  // Delete user by ID
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('USER_DELETE')")
  public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
    userService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
