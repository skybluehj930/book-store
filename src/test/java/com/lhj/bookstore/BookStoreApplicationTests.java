package com.lhj.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lhj.bookstore.entity.BookInfoEntity;
import com.lhj.bookstore.entity.ContractorEntity;
import com.lhj.bookstore.repository.BookInfoRepository;
import com.lhj.bookstore.repository.ContractorRepository;

@SpringBootTest
class BookStoreApplicationTests {

	@Autowired
	BookInfoRepository bookInfoRepository;
	
	@Autowired
	ContractorRepository contractorRepository;
	
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
	

}
