# book-store

## h2db와 jpa를 활용한 test code

### 1. 도서 등록
  Hello Java로 책을 등록후 반환 되는 entity를 null체크 해서 정상 등록 되는지 확인
  
### 2. 도서 조회
  Hello Java로 책을 등록후 Java로 %link& 검색이 되는지 체크.
  
### 3. 도서 수정
  Hello Java로 책을 등록후 Hello Spring으로 update하고 반환되는 title이 Hello Spring과 같은지 체크
  
### 4. 계약업체 등록
  01는 상태코드로 계약업체 등록후  반환 되는 entity를 null체크 해서 정상 등록 되는지 확인
  
### 5. 계약업체 조회
  01는 상태코드로 계약업체 등록후  01이라는 상태코드로 있는 계약업체를 조건 검색하고 반환된 상태코드가 01이 맞는지 체크
  
### 6. 계약업체 수정
  01는 상태코드로 계약업체 등록후  02이라는 상태코드로 update하고 반환되는 entity에 상태코드가 02로 정상 반영 되었는지 체크.
  
### 7. 공급 도서 등
  도서 -> 계약업체 -> 공급 차례대로 등록후 반환되는 entity를 fk로 공급 도서 entity에 담아 등록하고 반환되는 entity에 도서ID, 공급ID와 일치하는지 체크
  
### 8. 공급 도서 조회
  공급번호가 1인 공급 도서 정보를 join해서 조회하고 null 체크
  
### 9. 공급 도서 삭제
  공급번호가 1인 공급 도서 정보를 삭제하고 공급 테이블에서도 삭제