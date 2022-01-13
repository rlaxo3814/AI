  <%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=nhv560q8kp"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<meta charset="EUC-KR">
<title>Test Map</title>
</head>


<body>

<!-- ���̹� ������ �ѷ��� �� !  -->
<div id="map" style="width:100%;height:75vh; margin: 0 auto;"></div>


</body>


<script type="text/javascript">

$(function() {
	
	initMap();
	
});


function initMap() { 
	alert('init');
	var areaArr = new Array();  // ������ ��� �迭 ( ������/�����浵 )
	areaArr.push(
			/*������ �̸�*/			/*����*/					/*�浵*/				
		 {location : '����' , lat : '37.4959854' , lng : '127.0664091'},  // ������ �߽���ǥ
		 {location : '����' , lat : '37.5492077' , lng : '127.1464824'},  // ������ �߽���ǥ
		 {location : '����' , lat : '37.6469954' , lng : '127.0147158'},  // ���ϱ� �߽���ǥ
		 {location : '����' , lat : '37.5657617' , lng : '126.8226561'},  // ������ �߽���ǥ
		 {location : '����' , lat : '37.4603732' , lng : '126.9536086'},  // ���Ǳ� �߽���ǥ
		 {location : '����' , lat : '37.5574120' , lng : '127.0796211'},  // ������ �߽���ǥ
		 {location : '����' , lat : '37.4954856' , lng : '126.858121' },  // ���α� �߽���ǥ
		 {location : '��õ' , lat : '37.4600969' , lng : '126.9001546'},  // ��õ�� �߽���ǥ
		 {location : '���' , lat : '37.6377533' , lng : '127.0754623'},  // ����� �߽���ǥ
		 {location : '����' , lat : '37.6658609' , lng : '127.0317674'},  // ������ �߽���ǥ
		 {location : '���빮' , lat : '37.5838012' , lng : '127.0507003'},  // ���빮�� �߽���ǥ
		 {location : '����' , lat : '37.4965037' , lng : '126.9443073'},  // ���۱� �߽���ǥ
		 {location : '����' , lat : '37.5676507' , lng : '126.8854549'},  // ������ �߽���ǥ
		 {location : '���빮' , lat : '37.5820369' , lng : '126.9356665'},  // ���빮�� �߽���ǥ
		 {location : '����' , lat : '37.4769528' , lng : '127.0378103'},  // ���ʱ� �߽���ǥ
		 {location : '����' , lat : '37.5506753' , lng : '127.0409622'},  // ������ �߽���ǥ
		 {location : '����' , lat : '37.606991'  , lng : '127.0232185'},  // ���ϱ� �߽���ǥ
		 {location : '����' , lat : '37.5177941' , lng : '127.1127078'},  // ���ı� �߽���ǥ
		 {location : '��õ' , lat : '37.5270616' , lng : '126.8561534'},  // ��õ�� �߽���ǥ
		 {location : '������' , lat : '37.520641'  , lng : '126.9139242'},  // �������� �߽���ǥ
		 {location : '���' , lat : '37.5311008' , lng : '126.9810742'},  // ��걸 �߽���ǥ
		 {location : '����' , lat : '37.6176125' , lng : '126.9227004'},  // ���� �߽���ǥ
		 {location : '����' , lat : '37.5990998' , lng : '126.9861493'},  // ���α� �߽���ǥ
		 {location : '�߱�' , lat : '37.5579452' , lng : '126.9941904'},  // �߱� �߽���ǥ
		 {location : '�߶���' , lat : '37.598031'  , lng : '127.092931' }   // �߶��� �߽���ǥ
	);
	
	
	
	let markers = new Array(); // ��Ŀ ������ ��� �迭
	let infoWindows = new Array(); // ����â�� ��� �迭

	var map = new naver.maps.Map('map', {
		center: new naver.maps.LatLng(37.552758094502494, 126.98732600494576), //���� ���� ����
		zoom: 10
	});

	
	
	
	for (var i = 0; i < areaArr.length; i++) {

	    var marker = new naver.maps.Marker({
	        map: map,
	        title: areaArr[i].location, // ������ �̸� 
	        position: new naver.maps.LatLng(areaArr[i].lat , areaArr[i].lng) // �������� ���� �浵 �ֱ� 
	    });
	    
	    /* ����â */
		 var infoWindow = new naver.maps.InfoWindow({
		     content: '<div style="width:200px;text-align:center;padding:10px;"><b>' + areaArr[i].location + '</b><br> - ���̹� ���� - </div>'
		 }); // Ŭ������ �� ����� ���� HTML �ۼ�
	    
		 markers.push(marker); // ������ ��Ŀ�� �迭�� ��´�.
		 infoWindows.push(infoWindow); // ������ ����â�� �迭�� ��´�.
	}
    
	 
    function getClickHandler(seq) {
		
            return function(e) {  // ��Ŀ�� Ŭ���ϴ� �κ�
                var marker = markers[seq], // Ŭ���� ��Ŀ�� �������� ã�´�.
                    infoWindow = infoWindows[seq]; // Ŭ���� ��Ŀ�� �������� ã�´�

                if (infoWindow.getMap()) {
                    infoWindow.close();
                } else {
                    infoWindow.open(map, marker); // ǥ��
                }
    		}
    	}
    
    for (var i=0; i<markers.length; i++) {
    	//console.log(markers[i] , getClickHandler(i));
        naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i)); // Ŭ���� ��Ŀ �ڵ鷯
    }
}

</script>

</html>