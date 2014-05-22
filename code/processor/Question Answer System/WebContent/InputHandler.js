function showData() {
	var query = $('#query').val();
	$.ajax({
		url : "LoginServlet?query=" + query,
		type : "GET",
		dataType: 'json',
		async : false,
		success : function(data) {
			
			var answerPlace = document.getElementById("answerPlace");
			answerPlace.innerHTML = "";
			var table=CommonUtil.createChild("table","",new Array("align"),new Array("center"));
			if(data.hasOwnProperty("Result")){
				
			for(var obj in data.Result)
			{
					//entity_type , url , id , (ans) , infobox_type
			      var result = new Result(data.Result[obj]);
			      result.addResult(table);
			 //   var table=document.createElement("table");
			}
			answerPlace.appendChild(table);
	//		$('#response').text(data);
			$('#response').show();
			console.log("Showing Result");
			}else{
				$('#answerPlace').text("No Results available");
				$('#response').show();
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#answerPlace').text("No Results Found");
			$('#response').show();
			console.log("Showing Result");
		}
	});
}

function moreLikeThis(infobox_type,id){
	Result.moreLikeThis(infobox_type,id);
}