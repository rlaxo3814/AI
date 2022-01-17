<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>static</title>
</head>
<body>
<%
	StringBuilder builder = new StringBuilder("https://naveropenapi.apigw.ntruss.com/map-static/v2/raster-cors?");
	builder.append("w=300&h=300");
	builder.append("&center=126.9865479,37.5612557");
	builder.append("&level=13");
 	builder.append("&scale=2");
	builder.append("&markers=type:n|size:mid|pos:126.9865479%2037.5612557|label:1");
	builder.append("&markers=type:a|size:small|color:blue|pos:126.9870479%2037.5695075|label:a"); 
	builder.append("&markers=type:t|size:tiny|color:orange|pos:126.9950680%2037.5612557|label:"+URLEncoder.encode("충무로역","utf-8"));
	builder.append("&X-NCP-APIGW-API-KEY-ID=nhv560q8kp");
	
%>
<img src="<%=builder.toString()%>">

</body>
</html>