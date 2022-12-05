package com.sm.demo.service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import com.sm.demo.exception.*;
import com.sm.demo.entites.User;
import com.sm.demo.payload.UserDto;
import com.sm.demo.repo.UserRepo;

public class UserServiceIMP implements UserServiceI {

	@Autowired
	private UserRepo ur;

	@Override
	public UserDto createUser(UserDto udt) {

		User us = this.dtoToUser(udt);

		User saveUser = this.ur.save(us);

		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto udt, Integer userId) {

		User us = this.ur.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		us.setName(udt.getName());
		us.setAbout(udt.getAbout());
		us.setEmail(udt.getEmail());
		us.setPassword(udt.getPassword());

		User updateUser = this.ur.save(us);
		UserDto userToDto = this.userToDto(updateUser);

		return userToDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User us = this.ur.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		return this.userToDto(us);
	}

	@Override
	public List<UserDto> getAllData() {

		List<User> ur = this.ur.findAll();

		List<UserDto> userDto = ur.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {

		User us = this.ur.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		this.ur.delete(us);
	}

	private User dtoToUser(UserDto ud) {
		User us = new User();
		us.setId(ud.getId());
		us.setName(ud.getName());
		us.setEmail(ud.getEmail());
		us.setAbout(ud.getAbout());
		us.setPassword(ud.getPassword());
		return us;

	}

	private UserDto userToDto(User us) {

		UserDto ud = new UserDto();
		ud.setId(us.getId());
		ud.setName(us.getName());
		ud.setEmail(us.getEmail());
		ud.setAbout(us.getAbout());
		ud.setPassword(us.getPassword());

		return ud;

	}

}
