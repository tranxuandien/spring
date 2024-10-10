package com.example.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuddyInfoDto {
	public String buddyMail;
	public String studentMail;
	public String studentName;
}
