package com.lhj.bookstore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
public class SupplyBookEntity {

	@Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cont_id")
	private SupplyEntity supplyEntity; // 공급번호
	
	@ManyToOne
	@JoinColumn(name = "book_id")
	private BookInfoEntity bookInfoEntity; // 도서번호

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SupplyEntity getSupplyEntity() {
		return supplyEntity;
	}

	public void setSupplyEntity(SupplyEntity supplyEntity) {
		this.supplyEntity = supplyEntity;
	}

	public BookInfoEntity getBookInfoEntity() {
		return bookInfoEntity;
	}

	public void setBookInfoEntity(BookInfoEntity bookInfoEntity) {
		this.bookInfoEntity = bookInfoEntity;
	}
	
	
}
