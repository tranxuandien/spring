package com.example.lab.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChemicalImportRequestDto {

	public String barcode;
	@NotNull(message = "Nhập khối lượng hóa chất")
	public BigDecimal manufactoryQuantity;
	@NotNull(message = "Nhập hạn sử dụng")
	@NotEmpty(message = "Nhập hạn sử dụng")
	public String expiredDate;
	@NotNull(message = "Chọn vị trí đặt hóa chất")
	public Long position;
	public String chemicalStatus;
	public String purchaseSrc;
	public String impExpInfo;
}
