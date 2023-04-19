<%--<html>--%>
<%--<body>--%>
<%--<h2>Hello World!</h2>--%>
<%--<a href="user/findAll">getAll</a>--%>
<%--<a href="menu/getMenu">getMenu</a>--%>
<%--<a href="staff/getAllByProject">getStaff</a>--%>
<%--<form action="user/login" method="post">--%>
<%--    <input type="text" name="username">--%>
<%--    <input type="password" name="password">--%>
<%--    <input type="submit" value="login">--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script charset="utf-8" src="https://3gimg.qq.com/lightmap/components/geolocation/geolocation.min.js"></script>
    <script charset="utf-8" src="https://map.qq.com/api/js?v=2.exp&key=CLZBZ-CENER-J77WJ-WNN4Q-APRT2-A4FB4"></script>
    <style type="text/css">
        * {
            margin: 0px;
            padding: 0px;
        }
        body,button,input,select,textarea {
            font: 12px/16px Verdana, Helvetica, Arial, sans-serif;
        }
        #container {
            min-width: 600px;
            min-height: 767px;
        }
    </style>
</head>
<body>
<div id="container"></div>
<script>
    var geolocation = new qq.maps.Geolocation("CLZBZ-CENER-J77WJ-WNN4Q-APRT2-A4FB4", "myapp");
    var options = {
        timeout: 8000 //延时
    };
    var geocoder;
    var latLng;
    // 定位成功之后调用的方法
    function showPosition(position) {
        let lat = position.lat;
        let lng = position.lng;
        // 逆地址解析(经纬度到地名转换过程)
        geocoder = new qq.maps.Geocoder({
            complete: function(res) {
                console.log(res);
                // 标志位置
                var center = new qq.maps.LatLng(lat, lng);
                var map = new qq.maps.Map(document.getElementById('container'), {
                    center: center,
                    zoom: 13
                });
                //创建标记
                var marker = new qq.maps.Marker({
                    position: center,
                    map: map
                });
                //添加到提示窗
                var info = new qq.maps.InfoWindow({
                    map: map
                });
                //获取标记的点击事件
                qq.maps.event.addListener(marker, 'click', function() {
                    info.open();
                    info.setContent('<div style="text-align:center;white-space:nowrap;margin:10px;">' + res.detail.address + '</div>');
                    info.setPosition(center);
                });
            }
        });
        latLng = new qq.maps.LatLng(lat, lng);
        geocoder.getAddress(latLng);
    };
    function showErr() {
        console.log('定位失败');
    }
    geolocation.getLocation(showPosition, showErr, options);
</script>
</body>
</html>
