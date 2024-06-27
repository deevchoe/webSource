$(document).ready(function(){	/* 문서로딩이 완료 되면 function() */
	$("#btnSearch").click(function(){
		$("#show").empty();
		$("#showCount").empty();
		
		let bname = $("#bname").val();
		$.ajax({
			type: "get",
			url: "jq6.jsp",
			data: {"bname":bname},
			dataType: "json",
			success: function(datas) {
				let str = "<table border='1'>";
				str += "<tr><td>사번</td><td>직원명</td><td>직급</td><td>관리고객</td></tr>"
				let count = 0;
				$.each(datas, function(idx, entry){
					str += "<tr>";
					str += "<td>" + entry["jikwon_no"] + "</td>";
					if(Number(entry["jikwon_gogek"]) === 0){
						str += "<td>" + entry["jikwon_name"] + "</td>";						
					}else{
						str += "<td><a href='javascript:func(" + 
									entry["jikwon_no"] + ")'>" + // 함수는 여기서 끝나 함수는 no를 가져가
									entry["jikwon_name"] + "</a></td>";		// name이 하이퍼텍스트가 되는거
					}
					str += "<td>" + entry["jikwon_jik"] + "</td>";
					str += "<td>" + entry["jikwon_gogek"] + "</td>";
					str += "</tr>";
					count++;
				});
				str += "</table>";
				$("#show").append(str);
				$("#showCount").append(count);
			},
			error:function(){
				$("#show").text("에러 : " + status);
			}
		});
	});
});

function func(para){
	//alert(para);	// 직원의 번호가 잘 넘어가고 있는게 보인다.
	$("#gogek").empty();
	
	$.ajax({
		type: "get",
		url: "jq6gogek.jsp",
		data: {"arg":para},
		dataType: "json",
		success: function(datas) {
			let str = "<table border='1'>";
			str += "<tr><th>고객명</th><th>고객전화</th></tr>"
			let count = 0;
			$.each(datas, function(idx, entry){
				str += "<tr>";			
				str += "<td>" + entry["gogek_name"] + "</td>";
				str += "<td>" + entry["gogek_tel"] + "</td>";
				str += "</tr>";
				count++;
			});
			str += "</table>";
			$("#gogek").append(str);
		},
		error:function(){
			$("#gogek").text("에러 : " + status);
		}
	});
}