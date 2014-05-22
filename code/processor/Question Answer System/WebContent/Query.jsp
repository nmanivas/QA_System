<html lang="en">
<head>
<meta charset="utf-8">
<title>Pasanga Question Answer System</title>
<style>
fieldset {
	margin-bottom: 1em;
}

input {
	display: block;
	margin-bottom: .25em;
}

#print-output {
	width: 100%;
}

.print-output-line {
	white-space: pre;
	padding: 5px;
	font-family: monaco, monospace;
	font-size: .7em;
}

#query {
	font-size: 15px;
	height: 33px;
	left: 0;
	line-height: 33px;
	padding: 8px 8px 7px;
	top: 0;
}

.text {
	font: 24px "Georgia", Times New Roman, Times, serif;
}

.applebox {
	width: 90%;
	height: 55px;
	box-sizing: border-box;
	background: #f5f5f5;
	padding: 20px 20px 10px;
	margin: 10px 0 20px;
	border: 1px solid #ccc;
	border-radius: 4px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	-o-border-radius: 4px;
	-webkit-box-shadow: inset 0px 1px 1px rgba(0, 0, 0, .3);
	-moz-box-shadow: inset 0px 1px 1px rgba(0, 0, 0, .3);
	box-shadow: inset 0px 1px 1px rgba(0, 0, 0, .3);
}

.applebox .col {
	width: 140px
	float: left;
	margin: 0 0 0 30px;
}

.applewrap {
	width: 100%;
	display: block;
	height: auto;
	background: white;
	border: 1px solid;
	border-color: #e5e5e5 #dbdbdb #d2d2d2;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	-webkit-box-shadow: rgba(0, 0, 0, 0.3) 0 1px 3px;
	-moz-box-shadow: rgba(0, 0, 0, 0.3) 0 1px 3px;
	box-shadow: rgba(0, 0, 0, 0.3) 0 1px 3px;
}

.applewrap .col {
	float: left;
	box-sizing: border-box;
	width: 250px;
	height: 150px;
	padding: 16px 7px 6px 22px;
	font: 12px/18px "Lucida Grande", "Lucida Sans Unicode", Helvetica, Arial,
		Verdana, sans-serif;
	color: #343434;
	border-right: 1px solid #dadada;
}

.resultText {
	font: 12px "Georgia", Times New Roman, Times, serif;
}

.greenlinktxt {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 14px;
    font-weight: normal;
	color: #1E7B00;
    text-decoration: underline;
	cursor:pointer;
}

.greenlinkanstxt {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 14px;
    font-weight: normal;
	cursor:pointer;
}
</style>
<script src="jquery-1.9.1.js"></script>
<script src="Result.js"></script>
<script src="InputHandler.js"></script>
<script src="CommonUtil.js"></script>
</head>
<body>

	<div id="image" align="center">
		<img src="Pasanga.jpg"/>
	</div>

	
	<div style="padding-right: .5em; float: left; width: 98%"
		class="applebox" align="center">
		<div width="30%" style="valign:center" align="center">
			<span style="float:left;" class="text"> Enter your Question :
			</span>
		</div>
		<div style="float: left; width: 30%;valign:center">
			<input id="query" type="text" size=70>
		</div>
	</div>
	<br />

	<div class="applewrap" id="response" style="float: left;display:none" class="resultText">
		<div width="30%" align="center">
			<span class="text"> Result </span>
		</div>
		<br />
		<div id="answerPlace" class="greenlinkanstxt">
			
		</div>
	</div>


	<script>
var xTriggered = 0;
$( "#query" ).keypress(function( event ) {
  if ( event.which == 13 ) {
      event.preventDefault();
	  var msg = "Handler for .keypress() called Enter is print time(s).";
	  console.log(msg);
	  showData();
  }
}); 
</script>

</body>
</html>