<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>Test Map</title>
<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){

		$('#geo').click(function(){
			alert('geo')
			event.preventDefault();
			$.ajax({
		         type:"get",
		         url:"/stt/geoList",
		         contentType: "application/json",
			     success:function (data,textStatus){
			    	  alert(data);
			      	  for(var i =0;i<data.length;i++){
			    		  alert(data[i].lat+","+data[i].lang);
			    	  }
			    	  data = JSON.stringify(data);
			    	  alert(data); // 2번
			    	  resultText = JSON.parse(data);
			    	  for(var i =0;i<resultText.length;i++){
			    		  alert(resultText[i].lat+","+resultText[i].lang);
			    	  }
			    	  
			    	 
			     },
			     error:function(data,textStatus){
			        alert("에러가 발생했습니다.");
			     },
			     complete:function(data,textStatus){
			    	 
			     }
		});
	});
	});

</script>
<body>
<input type="button" id="geo" value="geo List">
</body>
</html>