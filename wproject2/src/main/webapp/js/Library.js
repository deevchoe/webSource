class Library{
	constructor(name){
		this.name = name;	// 도서관 이름
		this.catalog = [];	// 초기 카탈로그용 빈 배열
	}
	
	// 도서관에 책이나 잡지 추가
	addItem(item){
		this.catalog.push(item);	// 배열에 집어넣을 땐 push라고 그랬지~
		console.log(`${item.title}이(가) 추가되었습니다.`);
	}
	
	// 제목으로 도서관에서 책이나 잡지 검색
	findItem(title){
		// catalog 배열에서 제목이 일치하는 첫번째 항목을 반환
		return this.catalog.find(item => item.title === title);
	}
	
	// 도서관의 책이나 잡지 모두 출력
	listItems(){
		console.log(`${this.name} 도서관의 책 목록 : `);
		this.catalog.forEach(item => console.log(item.toString()));
	}
}

export default Library;	// 라이브러리 모듈로 내보내는거