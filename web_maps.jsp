<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>������ ���� ǥ���ϱ�</title>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=nhv560q8kp"></script>
</head>
<body>
<div id="map" style="width:100%;height:400px;"></div>

<script>

var HOME_PATH = 'resources/imgs/';

var cityhall = new naver.maps.LatLng(37.5666805, 126.9784147),
    map = new naver.maps.Map('map', {
        center: cityhall.destinationPoint(0, 500),
        zoom: 15
    }),
    marker = new naver.maps.Marker({
        map: map,
        position: cityhall
    });

var contentString = [
        '<div class="iw_inner">',
        '   <h3>����Ư����û</h3>',
        '   <p>����Ư���� �߱� �����1�� 31 | ����Ư���� �߱� ������� 110 ����Ư����û<br />',
        '       <img src="'+ HOME_PATH +'hi-seoul.jpg" width="55" height="55" alt="�����û" class="thumb" /><br />',
        '       02-120 | ����,��ȸ��� &gt; Ư��,������û<br />',
        '       <a href="http://www.seoul.go.kr" target="_blank">www.seoul.go.kr/</a>',
        '   </p>',
        '</div>'
    ].join('');

var infowindow = new naver.maps.InfoWindow({
    content: contentString
});

naver.maps.Event.addListener(marker, "click", function(e) {
    if (infowindow.getMap()) {
        infowindow.close();
    } else {
        infowindow.open(map, marker);
    }
});

infowindow.open(map, marker);
alert(window.HOME_PATH);
</script>
</body>
</html>