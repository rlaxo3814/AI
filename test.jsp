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
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=nhv560q8kp"></script>
<script type="text/javascript">
	$(document).ready(function(){

		$('#send').click(function(){
			alert('sendAddr')
			event.preventDefault();
		$.ajax({
	         type:"get",
	         url:"/stt/kthmap3",
	         contentType: "application/json",
	         data :{"coords":$("#lang").val()+","+$("#lat").val()},
		     success:function (data,textStatus){
		    	  //alert(data); // 2번
		    	  resultText = JSON.parse(data);
		    	  text = resultText.results[0].region
		    	  //area1.name+","+resultText.results[1]
				  keys = Object.keys(text);
		    	  address = ""
		    	  for(var i=1;i<keys.length;i++){
		    		  address += text[keys[i]].name+" ";
		    	  }
		    	  alert(address);
		    	  $('#span_addr').text(address);
		     },
		     error:function(data,textStatus){
		        alert("에러가 발생했습니다.");
		     },
		     complete:function(data,textStatus){
		    	 
		     }
		});
	});
		
		$('#sendLatLang').click(function(){
			var lat = $('#lat').val();
			var lang = $('#lang').val();
			var loc = $('#map')
			alert('send')
			var mapOptions = {
				    center: new naver.maps.LatLng(lat, lang),
				    zoom: 15
				};
				var map = new naver.maps.Map('map', mapOptions);
				var marker = new naver.maps.Marker({
				    position: new naver.maps.LatLng(lat, lang),
				    map: map
				}); 
		});
		
		$('#sendMark').click(function(){
			alert('sendMark')
				event.preventDefault();
			$.ajax({
		         type:"get",
		         url:"/stt/kthmap2",
		         contentType: "application/json",
		         data :{"addr":$("#addr").val()},
			     success:function (data,textStatus){
			    	  //alert(data); // 2번
			    	  resultText = JSON.parse(data);
			    	  //text = resultText.results[0].region.area1.name+","+resultText.results[1]
			    	  var lang1 = resultText.addresses[0].x;
			    	  var lat1 = resultText.addresses[0].y;
			    	  alert(lat1+", "+lang1);
			    	  $('#span_lat').text(lat1);
			    	  $('#span_lang').text(lang1);
			    	 // alert(text); // 3번
			    	 // $('#message').text(text);
			    	  var mapOptions = {
							    center: new naver.maps.LatLng(lat1, lang1),
							    zoom: 15
							};
					var map = new naver.maps.Map('map', mapOptions);
					var marker = new naver.maps.Marker({
					    position: new naver.maps.LatLng(lat1, lang1),
					    map: map
					}); 
			     },
			     error:function(data,textStatus){
			        alert("에러가 발생했습니다.");
			     },
			     complete:function(data,textStatus){
			    	 
			     }
			  });
			
			
			var mapOptions = {
			    center: new naver.maps.LatLng(lat, lang),
			    zoom: 15
			};
			var map = new naver.maps.Map('map', mapOptions);
			var marker = new naver.maps.Marker({
			    position: new naver.maps.LatLng(lat, lang),
			    map: map
			}); 
			
			
		});
		
	});

	
	
	
</script>

</head>
<body>
<a href="">테스트</a>
<form method="get" action="">
	위도 : <input type="text" name="geo1" id="lat" value="37.5666805">
	경도 : <input type="text" name="geo2" id="lang" value="126.9784147">  
	<input type="button" name="send" id="sendLatLang" value="Test1">
	<input type="button" name="send" id="send" value="TestAddr"><br>
	주소 <input type="text" name="addr" id="addr" size="30" value='서울특별시 강남구 언주로 508'>
	<input type="button" name="send" id="sendMark" value="Test2"><br>
</form>
위도 <span id='span_lat'></span><br>
경도 <span id='span_lang'></span><br>
주소 <span id='span_addr'></span>
<div id="map" style="width:100%;height:400px;"></div>
</body>
</html>