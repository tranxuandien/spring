package com.example.lab.dto.request;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class BuddyRegisterRequestDto {
	public Long buddy;
	public List<Long> users;
}
