package com.example.lab.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeviceRegisterRequestDto {

	@NotEmpty
	@NotNull
	public String name;
	@NotEmpty
	@NotNull
	public String position;
	public String otherInfo;

}
