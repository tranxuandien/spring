package com.example.lab.model.chemical;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EmbeddedChemicalInventory {
	@Column(name = "quantity",table = "chemical_inventory")
	private BigDecimal quantity;

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
}
