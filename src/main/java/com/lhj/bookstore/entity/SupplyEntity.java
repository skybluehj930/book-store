package com.lhj.bookstore.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
public class SupplyEntity {
	
	@Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 공급번호
	
	@ManyToOne
	@JoinColumn(name = "cont_id")
	private ContractorEntity contractorEntity; // 계약번호
	
	private Date supply_at; // 공급일자

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ContractorEntity getContractorEntity() {
		return contractorEntity;
	}

	public void setContractorEntity(ContractorEntity contractorEntity) {
		this.contractorEntity = contractorEntity;
	}

	public Date getSupply_at() {
		return supply_at;
	}

	public void setSupply_at(Date supply_at) {
		this.supply_at = supply_at;
	}
	
	
}
