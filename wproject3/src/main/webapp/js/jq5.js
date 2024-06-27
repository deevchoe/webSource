$(document).ready(function() {
	$("#btnAll").click(function() {
		//alert("a");
		$("#show").empty();
		$("#count").empty();

		$.ajax({
			type: "get",
			url: "jq5.jsp",
			data: { "gubun": "all" },
			dataType: "xml",
			success: function(datas) {
				let str = "<table border='1'>";
				str += "<tr><td>사번</td><td>이름</td><td>직급</td><td>연봉</td></tr>"
				let count = 0;
				$(datas).find("jikwon").each(function() {
					str += "<tr>";
					str += "<td>" + $(this).find("sabun").text() + "</td>";
					str += "<td>" + $(this).find("irum").text() + "</td>";
					str += "<td>" + $(this).find("jik").text() + "</td>";
					str += "<td>" + $(this).find("pay").text() + "</td>";
					count++;
				});
				str += "</table>"
				$("#show").append(str);
				$("#count").append(count);
			}
		})
		.fail(function() {
			$("#show").text('all 처리 오류');
		});
	});

	//---------------------------------------------------------

	$("#btnSearch").click(function() {
		$("#show").empty();
		$("#count").empty();
		let name = $("#name").val();
		
		if(name === ""){
			alert("직원명을 입력하세요");
			return false;
		}
		//alert(name);
		
		$.ajax({
			type: "get",
			url: "jq5.jsp",
			data: { "gubun": "search","name":name},	// "search" : 문자열    name : 변수명
			dataType: "xml",
			success: function(datas) {
				let str = "<table border='1'>";
				str += "<tr><td>사번</td><td>이름</td><td>직급</td><td>연봉</td></tr>"
				let count = 0;
				
				if($(datas).find("jikwon").length === 0){
					str = "해당 검색 결과는 없습니다."
					$("#show").append(str);
					$("#count").append("0");
					return;
				}
				
				$(datas).find("jikwon").each(function() {
					str += "<tr>";
					str += "<td>" + $(this).find("sabun").text() + "</td>";
					str += "<td>" + $(this).find("irum").text() + "</td>";
					str += "<td>" + $(this).find("jik").text() + "</td>";
					str += "<td>" + $(this).find("pay").text() + "</td>";
					count++;
				});
				str += "</table>"
				$("#show").append(str);
				$("#count").append(count);
			}
		})
		.fail(function() {
			$("#show").text('search 처리 오류');
		});
	});
});