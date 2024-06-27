import Book from './Book.js'

class Magazine extends Book{
	constructor(title, author, isbn, issueNum){	// isbn : 책들에 고유번호를 준 것
		super(title, author, isbn);
		this.issueNum = issueNum;	// 잡지 호수
	}
	
	toString(){		// 책 정보를 문자열로 반환
		return `책 제목 : ${this.title} | 저자 : ${this.author} | ISBN : ${this.isbn} | 호 수 : ${this.issueNum}`;
	}
}

export default Magazine;