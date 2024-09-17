package com.example.lab.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommonResponseEntity {
	private Object data;
	private String errorMessage;
	private String errorCode;
	
}
