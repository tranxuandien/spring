package com.example.lab.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab.common.message.CommonMessage;
import com.example.lab.common.message.ErrorMessage;
import com.example.lab.dto.request.BuddyRegisterRequestDto;
import com.example.lab.dto.response.CommonResponseEntity;
import com.example.lab.dto.response.CommonSelectResponseDto;
import com.example.lab.dto.response.UserInfoResponseDto;
import com.example.lab.dto.response.UserResponseDto;
import com.example.lab.service.securityServices.UserService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/master/list")
	public ResponseEntity<List<UserResponseDto>> getAllUsers() {
		List<UserResponseDto> users = userService.getAllUser();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/admin/user/list")
	public ResponseEntity<?> getUsers(@PathParam("name") String name) {
		List<UserInfoResponseDto> users = userService.getUsers(name);
		return ResponseEntity.status(HttpStatus.OK).body(CommonResponseEntity.builder().data(users).build());
	}

	@GetMapping("/admin/buddy/list")
	public ResponseEntity<?> getBuddy() {
		List<CommonSelectResponseDto> dtos = userService.getUsersRoleBuddy();
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/admin/student/list")
	public ResponseEntity<?> getStudent() {
		List<CommonSelectResponseDto> dtos = userService.getUsersRoleUser();
		return ResponseEntity.ok(dtos);
	}

	@PostMapping("/admin/buddy/register")
	public ResponseEntity<?> buddyRegister(@RequestBody BuddyRegisterRequestDto dto) {
		try {
			userService.buddyRegister(dto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(CommonResponseEntity.builder().message(ErrorMessage.USER_BUDDY_NOT_REGISTER).build());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(CommonResponseEntity.builder().message(CommonMessage.USER_BUDDY_REGISTER).build());
	}

	@GetMapping("/admin/user/active")
	public ResponseEntity<?> activeUser(@PathParam("id") Long id) {
		try {
			userService.active(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.AUTH_NOT_ACTIVE_USER).build());
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(CommonResponseEntity.builder().message(CommonMessage.AUTH_ACTIVED_USER).build());
	}

	@DeleteMapping("/admin/user/delete/{id}")
	public ResponseEntity<?> deleteChemical(@PathVariable(value = "id") Long id) {
		try {
			userService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.USER_CANNOT_DELETED).build());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(CommonResponseEntity.builder().message(CommonMessage.USER_DELETED).build());
	}
}
