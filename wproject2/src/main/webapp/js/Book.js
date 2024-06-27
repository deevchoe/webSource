// 일반 도서
class Book{
	constructor(title, author, isbn){	// isbn : 책들에 고유번호를 준 것
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}
	
	toString(){		// 책 정보를 문자열로 반환
		return `책 제목 : ${this.title} | 저자 : ${this.author} | ISBN : ${this.isbn}`;
	}
}

export default Book;