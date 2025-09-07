package com.kushg.rasoiRun.service;

import com.kushg.rasoiRun.entity.UserEntity;
import com.kushg.rasoiRun.io.UserRequest;
import com.kushg.rasoiRun.io.UserResponse;
import com.kushg.rasoiRun.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public UserResponse registerUser(UserRequest request) {
      UserEntity newUser = convertToEntity(request);
      newUser =  userRepository.save(newUser);
      return convertToResponse(newUser);
    }

    @Override
    public String findByUserId() {
       String loggedInUserEmail = authenticationFacade.getAuthentication().getName();
       UserEntity loggegInUser = userRepository.findByEmail(loggedInUserEmail).orElseThrow(() -> new UsernameNotFoundException("User not found"));
       return loggegInUser.getId();
    }

    private UserEntity convertToEntity(UserRequest request){
       return UserEntity.builder()
               .email(request.getEmail())
               .password(passwordEncoder.encode(request.getPassword()))
               .name(request.getName())
               .build();
    }

    private UserResponse convertToResponse(UserEntity registerUser){
       return UserResponse.builder()
               .id(registerUser.getId())
               .name(registerUser.getName())
               .email(registerUser.getEmail())
               .build();
    }
}
