<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="./css/style.css" rel="stylesheet">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<title>登录</title>
<link rel="manifest" href="./manifest.json">
<link rel="shortcut icon" href="./favicon.ico">
<script src="./config/browser-polyfill.min.js"></script>
<script src="./config/HBCJ.js"></script>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

</head>
<body>
	<noscript>请设置您的浏览器允许使用javascript。</noscript>
	<script type="text/javascript" src="./config/strophe-1.2.8.js"></script>
	<div id="root" style="height:100%"></div>
	<div id="loading" style="height:100%;margin-top:100px"></div>
	<script type="text/javascript" src="./static/js/main.f69cb37a.js"></script>
</body>
</html>