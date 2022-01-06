package com.lhj.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lhj.bookstore.entity.BookInfoEntity;
import com.lhj.bookstore.entity.ContractorEntity;
import com.lhj.bookstore.entity.SupplyBookEntity;
import com.lhj.bookstore.entity.SupplyEntity;
import com.lhj.bookstore.repository.BookInfoRepository;
import com.lhj.bookstore.repository.ContractorRepository;
import com.lhj.bookstore.repository.SupplyBookRepository;
import com.lhj.bookstore.repository.SupplyRepository;

@SpringBootTest
class BookStoreApplicationTests {
	
	@Autowired
	private BookInfoRepository bookInfoRepository;
	
	@Autowired
	private ContractorRepository contractorRepository;
	
	@Autowired
	private SupplyRepository supplyRepository;
	
	@Autowired
	private SupplyBookRepository supplyBookRepository;
	
	@DisplayName("1. 도서 등록")
	@Test
	public void bookRegister() {
		
		// given
		BookInfoEntity bookInfoEntity = new BookInfoEntity();
		bookInfoEntity.setTitle("Hello Java");
		bookInfoEntity.setType("T001");	
		bookInfoEntity.setSupPrice(1000);
		bookInfoEntity.setFixPrice(2000);
		bookInfoEntity.setQuantity(100);
		bookInfoEntity.setWriter("홍길동");
		bookInfoEntity.setDiscount(5);
		bookInfoEntity.setCreatedAt(new Date());
		
		// when
		BookInfoEntity bookInfoConfirm = bookInfoRepository.save(bookInfoEntity);
		
		// then
		assertThat(bookInfoConfirm).isNotNull();
	}
	
	@DisplayName("2. 도서 조회")
	@Test
	public void bookSearch() {
		
		// given
		bookRegister();
		
		String title = "Java";
		
		// when
		List<BookInfoEntity> bookInfoList = bookInfoRepository.findByTitleContaining(title);

		
		// then
		assertThat(bookInfoList.get(0).getTitle().contains(title));
		
	}
	
	@DisplayName("3. 도서 수정")
	@Test
	public void bookModify() {
		
		// given
		bookRegister();
		
		long bookId = 1L;
		BookInfoEntity bookInfoEntity = bookInfoRepository.findById(bookId).orElse(null);
		
		String title = "Hello Spring";
		
		// when
		bookInfoEntity.setTitle(title);
		BookInfoEntity bookInfoConfirm = bookInfoRepository.save(bookInfoEntity);
		
		// then
		assertEquals(bookInfoConfirm.getTitle(), title);
	}
	
	@DisplayName("4. 계약업체 등록")
	@Test
	public void contractorRegister() {
		
		// given
		ContractorEntity contractorEntity = new ContractorEntity();
		contractorEntity.setStateCd("01");
		contractorEntity.setContractAt(new Date());
		contractorEntity.setLowest(5);
		
		// when
		ContractorEntity contractorConfirm = contractorRepository.save(contractorEntity);
		
		// then
		assertThat(contractorConfirm).isNotNull();
		
	}
	
	@DisplayName("5. 계약업체 조회")
	@Test
	public void contractorSearch() {
		
		// given
		contractorRegister();
		
		String stateCd = "01";
		
		// when
		List<ContractorEntity> contractorList = contractorRepository.findByStateCd(stateCd);
		
		// then
		assertEquals(contractorList.get(0).getStateCd(), stateCd);
		
	}
	
	@DisplayName("6. 계약업체 수정")
	@Test
	public void contractorModify() {
		
		// given
		contractorRegister();
		
		String stateCd = "02";
		Long contId = 1L;
		ContractorEntity contractorConfirm = null;
		
		ContractorEntity contractorEntity = contractorRepository.findById(contId).orElse(null);
		
		// when
		if(contractorEntity != null) {
			contractorEntity.setStateCd(stateCd);
			contractorConfirm = contractorRepository.save(contractorEntity);
		}
		
		
		// then
		assertEquals(contractorConfirm.getStateCd(), stateCd);
		
	}
	
	
	@DisplayName("7. 공급 도서 등록")
	@Test
	public void supplyBookRegister() {
		
		// given
		// 도서 등록
		BookInfoEntity bookInfoEntity = new BookInfoEntity();
		bookInfoEntity.setTitle("Hello Java");
		bookInfoEntity.setType("T001");	
		bookInfoEntity.setSupPrice(1000);
		bookInfoEntity.setFixPrice(2000);
		bookInfoEntity.setQuantity(100);
		bookInfoEntity.setWriter("홍길동");
		bookInfoEntity.setDiscount(5);
		bookInfoEntity.setCreatedAt(new Date());
		BookInfoEntity bookInfoRes = bookInfoRepository.save(bookInfoEntity);
		Long bookId = bookInfoRes.getId();
		
		// 계약업체 등록
		ContractorEntity contractorEntity = new ContractorEntity();
		contractorEntity.setStateCd("01");
		contractorEntity.setContractAt(new Date());
		contractorEntity.setLowest(5);
		ContractorEntity contractorRes= contractorRepository.save(contractorEntity);
		
		// 공급 등록
		SupplyEntity supplyEntity = new SupplyEntity();
		supplyEntity.setContractor(contractorRes);
		supplyEntity.setSupplyAt(new Date());
		SupplyEntity supplyRes = supplyRepository.save(supplyEntity);
		Long supId = supplyRes.getId();
		
		// when
		// 공급 도서 등록 
		SupplyBookEntity supplyBookEntity = new SupplyBookEntity();
		supplyBookEntity.setBookInfo(bookInfoRes);
		supplyBookEntity.setSupply(supplyRes);
		SupplyBookEntity supplyBookRes = supplyBookRepository.save(supplyBookEntity);
		
		// then
		assertEquals(bookId, supplyBookRes.getBookInfo().getId());
		assertEquals(supId, supplyBookRes.getSupply().getId());
		
	}
	
	
	@DisplayName("8. 공급 도서 조회")
	@Test
	public void supplyBookSearch() {
		
		// given
		// 공급 도서 등록 
		supplyBookRegister();
		Long supplyId = 1L;
		
		// when
		List<SupplyBookEntity> supplyBookRes = supplyBookRepository.findBySupplyId(supplyId);
		
		// then
		assertThat(supplyBookRes.get(0).getBookInfo()).isNotNull();
		
	}
	
	
	@DisplayName("9. 공급 도서 삭제")
	@Test
	public void supplyBookRemove() {
		
		// given
		// 공급 도서 등록 
		supplyBookRegister();
		Long supplyId = 1L;
		
		// 공급 도서 조회
		List<SupplyBookEntity> supplyBookList = supplyBookRepository.findBySupplyId(supplyId);
		
		// 공급 조회
		SupplyEntity supplyRes = supplyRepository.findById(supplyId).orElse(null);
		
		// whens
		// 공급 도서 삭제
		for(SupplyBookEntity supplyBookEntity : supplyBookList) {
			supplyBookRepository.delete(supplyBookEntity);
		}
		// 공급 삭제
		if(supplyRes != null) {
			supplyRepository.delete(supplyRes);
		}
		
		List<SupplyBookEntity> supplyBookConfirm = supplyBookRepository.findBySupplyId(supplyId);
		SupplyEntity supplyConfirm = supplyRepository.findById(supplyId).orElse(null);
		
		// then
		assertEquals(supplyBookConfirm.size(), 0);
		assertThat(supplyConfirm).isNull();
		
	}

}
