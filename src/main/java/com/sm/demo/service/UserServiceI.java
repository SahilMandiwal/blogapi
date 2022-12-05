package com.sm.demo.service;

import java.util.List;

import com.sm.demo.payload.UserDto;

public interface UserServiceI {
	
	UserDto createUser(UserDto udt);
	
	UserDto updateUser(UserDto udt, Integer userId);
	
	UserDto getUserById (Integer userId);
	
	List<UserDto> getAllData();
	
	void  deleteUser(Integer userId);
}
