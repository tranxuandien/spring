package com.example.lab.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportUsingDeviceStatusDto {
	Long id;
	String deviceStatusDetail;
}
