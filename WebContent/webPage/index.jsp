<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello there!</title>
</head>
<body>
<div>
Entries in the database:<br>
<textarea rows="12" cols="65" id="dbentries"></textarea>
</div>
<div>
Input IDX: <input type="text" id="input" name="input"><button onclick="executeProcedure()">Execute!</button>
</div>
<div>
Output:<br> 
<textarea id="output" rows="12" cols="65" id="output"></textarea>
</div>
<script>
	function executeProcedure(){
		var param = "?input=" + document.getElementById("input").value;
		
		var http = new XMLHttpRequest();
		var url = 'http://localhost:8080/WebPage/webPage/proxy' + param;
		http.open('POST', url, true);

		//Send the proper header information along with the request
		http.setRequestHeader('Content-type', 'text/plain');

		http.onreadystatechange = function() {//Call a function when the state changes.
		    if(http.readyState == 4 && http.status == 200) {
		        document.getElementById("output").innerHTML = http.responseText;
		    }
		}
		http.send(param);
	}
	
	function init() {
	    var checkForChanges = function() {
			
	    	var oldData = document.getElementById("dbentries").innerHTML;
	    	var param = "?input=";
			
			var http = new XMLHttpRequest();
			var url = 'http://localhost:8080/WebPage/webPage/proxy' + param;
			http.open('POST', url, true);

			http.setRequestHeader('Content-type', 'text/plain');

			//Called when the state of the call changes
			http.onreadystatechange = function() {
			    if(http.readyState == 4 && http.status == 200) {
			        if(oldData !== http.responseText){
			        	document.getElementById("dbentries").innerHTML = http.responseText;
			        };
			    }
			}
			
			http.send(param);
	        var rand = Math.round(Math.random() * (5000)) + 5000; // generate new time (between 10s and 5s)
	        setTimeout(checkForChanges, rand);
	    }
	    checkForChanges();
	}
	
	window.setTimeout(init(), 1);

</script>
</body>
</html>