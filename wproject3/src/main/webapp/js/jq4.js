$(document).ready(function() {
	// html 문서를 text로 읽기 
	$('#test1').click(function() {
		//alert("a");
		$("#disp").empty();	// 출력 장소를 깨끗하게 비우고

		//$("#disp").load("jq4.html");// 로딩~     비동기 방식~~~ ajax~~~
		$("#disp").hide().load("jq4.html", function() {
			$(this).fadeIn();	// this : 현재 이벤트가 발생된 객체	fadeIn : effect 처리
		});// 로딩~     비동기 방식~~~ ajax~~~

	});

	// json 읽기
	$('#test2').click(function() {
		$("#disp").empty();

		$.getJSON('jq4json.jsp', function(datas) {
			//alert(datas);
			//$("#disp").text(datas);

			let items = [];
			let str = "<ul>";
			$.each(datas.sangpum, function(index, data) {
				console.log(data);

				//alert(index + " " + data);

				let s = "<li>" + (index + 1) + ") " + data["code"] + " " +
					data["sang"] + " " +
					data["su"] + " " +
					data.dan + "</li>";
				items.push(s);
			});
			str += items.join('') + "</ul>";
			// 배열의 모든 항목을 하나의 문자열로 결합

			$("#disp").html(str);
		});
	});

	// xml 읽기
	$('#test3').click(function() {
		//alert("3");
		$.get('jq4xml.jsp', function(datas) {
			//$.post('jq4xml.jsp', function(datas){
			//alert(datas);
			// 하위 elmemt(요소)를 검색할 때 find() 사용
			//alert($(datas).find('sangpum').length);
			$('#disp').empty();

			$(datas).find('sangpum').each(function() {
				let sangpum = $(this);
				let str = "<div>";
				str += sangpum.find('code').text() + " ";
				str += sangpum.find('sangirum').text() + " ";
				str += sangpum.find('su').text() + " ";
				str += sangpum.find('danga').text();
				str += "</div>";
				$("#disp").append(str);
			});
		}).fail(function() {
			$("#disp").text('test3 처리 오류');
		});
	});

	// json 읽기 2
	$('#test4').click(function() {
		//alert("4");
		$("#disp").empty();

		$.ajax({		// jquery에서 ajax메소드를 쓴다하면 이 방법을 제일 많이 씀
			type: "get",	// 요청방식    post도 있어요!
			url: 'jq4json.jsp',
			//data:{'code':3, 'sang':'book'},	// jq4json.jsp?code=3&sang=book
			dataType: "json",	// 반환 data type
			success: function(datas) {
				//alert(datas);
				let str = "";
				let count = 0;
				str += "<table border = '1'>";
				str += "<tr><th>코드</th><th>품명</th><th>수량</th><th>단가</th></tr>"

				$.each(datas.sangpum, function(idx, data) {
					str += "<tr>";
					str += "<td>" + data["code"] + "</td>";
					str += "<td>" + data["sang"] + "</td>";
					str += "<td>" + data["su"] + "</td>";
					str += "<td>" + data.dan + "</td>";
					str += "</tr>";
					count++;
				});
				str += "</table>";
				$("#disp").append(str);
				$("#disp").append(`건수 : ${count}개`);

			},
			error: function() {
				$("#disp").text('test4 처리 오류');
			}
		});
	});

	// xml 읽기 2
	$('#test5').on("click", function() {
		//alert("5");
		$("#disp").empty();

		$.ajax({
			type: "post",	// 요청방식    post도 있어요!
			url: 'jq4xml.jsp',
			dataType: "xml",	// 반환 data type
			success: function(datas) {
				//alert(datas);
				let count = 0;
				let str = "<table border = '1'>";
				str += "<tr><th>코드</th><th>품명</th><th>수량</th><th>단가</th></tr>"

				$(datas).find('sangpum').each(function(){
					str += "<tr>";
					str += "<td>" + $(this).find("code").text() + "</td>";
					str += "<td>" + $(this).find("sangirum").text() + "</td>";
					str += "<td>" + $(this).find("su").text() + "</td>";
					str += "<td>" + $(this).find("danga").text() + "</td>";
					str += "</tr>";
					count++;
				});
				str += "</table>";
				$("#disp").append(str);
				$("#disp").append(`건수(xml) : ${count}개`);

			},
			error: function() {
				$("#disp").text('test5 처리 오류');
			}
		});

	});
});