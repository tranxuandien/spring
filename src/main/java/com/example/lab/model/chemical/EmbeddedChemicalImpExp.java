package com.example.lab.model.chemical;

import java.math.BigDecimal;

import com.example.lab.model.UserInfo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Embeddable
public class EmbeddedChemicalImpExp {
	@Column(name = "type",table = "chemical_imp_exp")
	private String type;
	@Basic(optional = false)
	@Column(name = "quantity",table = "chemical_imp_exp")
	private BigDecimal quantity;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "imp_user",table = "chemical_imp_exp")
	private UserInfo impUser;
	
	@Column(name = "exp_user",table = "chemical_imp_exp")
	private Long expUser;
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public UserInfo getImpUser() {
		return impUser;
	}

	public void setImpUser(UserInfo impUser) {
		this.impUser = impUser;
	}

	public Long getExpUser() {
		return expUser;
	}

	public void setExpUser(Long expUser) {
		this.expUser = expUser;
	}
	
	
}
