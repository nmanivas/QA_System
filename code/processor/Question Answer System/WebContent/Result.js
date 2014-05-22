var Result = function(result) {
	this.entity_name = result.entity_name;
	this.id = result.id;
	this.title = result.title;
	this.type = result.infobox_type;
	this.query_field = result.query_field.replace("_", " ");
	this.query_result = result.query_result;
	this.wikidoc_title = result.wikidoc_title;
}

Result.prototype.addResult = function(table) {
	var tr = CommonUtil.createChild("tr", "", new Array("id", "align"),
			new Array("resultTr_" + this.id, "center"));
	var moreLikeThisTr = CommonUtil.createChild("tr", "", new Array("id",
			"align"), new Array("moreLikeThisTr_" + this.id, "center"));
	var td = CommonUtil.createChild("td", "", new Array("id", "align"),
			new Array("resultTd_" + this.id, "center"));
	var linkName = this.wikidoc_title.replace(" ", "_");

	var wikiLink = CommonUtil.createChild("a", "greenlinktxt", new Array("href", "target"),
			new Array("http://en.wikipedia.org/wiki/" + linkName, "_blank"));
	wikiLink.innerHTML = this.entity_name;
	td.appendChild(wikiLink);
	td.innerHTML += " " + this.query_result;
	var moreLikeThisTd = CommonUtil.createChild("td", "", new Array("id"),
			new Array("moreLikeThisTd_" + this.id, "center"));

	var link = CommonUtil.createChild("a", "greenlinktxt", new Array("href"), new Array(
			"javascript:moreLikeThis(" + this.type + "," + this.id + ")"));
	link.innerHTML = "More Like " + this.entity_name;
	moreLikeThisTd.appendChild(link);
	tr.appendChild(td);
	moreLikeThisTr.appendChild(moreLikeThisTd);
	table.appendChild(tr);
	table.appendChild(moreLikeThisTr);
}

Result.moreLikeThis = function(infobox_type, id) {
	// Ajax call with infobox_type and id
	$.ajax({
		url : "MoreLikeServlet?infobox_type=" + infobox_type + "&id=" + id,
		type : "GET",
		dataType : 'json',
		async : false,
		success : function(data) {
			var count = 0;
			var td = CommonUtil.createChild("td", "", new Array("id", "align"),
					new Array("moreLikeThis_" + id, "center"));
			if (data.hasOwnProperty("Result")) {
				for ( var obj in data.Result) {
					// entity_type , url , id , (ans) , infobox_type
					// var result = new Result(data.Result[obj]);
					if (count < 5) {
						var linkName = data.Result[obj].wikidoc_title.replace(
								" ", "_");
						var wikiLink = CommonUtil.createChild("a", "greenlinktxt",
								new Array("href", "target"), new Array(
										"http://en.wikipedia.org/wiki/"
												+ linkName, "_blank"));
						wikiLink.innerHTML = linkName+"\t , \t ";
						td.appendChild(wikiLink);
						count++;
					} else {
						break;
					}

					// result.addResult(table);
					// var table=document.createElement("table");
				}
			} else {
				td.innerHTML = "No Matchng Entities found";
			}
			document.getElementById("moreLikeThisTr_" + id).innerHTML = "";
			document.getElementById("moreLikeThisTr_" + id).appendChild(td);
			// answerPlace.appendChild(table);
			// $('#response').text(data);
			// $('#response').show();
			console.log("Showing Result");
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#answerPlace').text("No Results Found");
			$('#response').show();
			console.log("Showing Result");
		}
	});
}