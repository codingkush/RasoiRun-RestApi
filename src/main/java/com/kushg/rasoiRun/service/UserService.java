package com.kushg.rasoiRun.service;

import com.kushg.rasoiRun.io.UserRequest;
import com.kushg.rasoiRun.io.UserResponse;

public interface UserService {

   UserResponse registerUser(UserRequest request);
   String findByUserId();
}
