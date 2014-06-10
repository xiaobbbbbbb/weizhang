<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
<title></title>

<link href="${ctx}/media/bootstrap/bootstrap.min.css?v=${version}"
	rel="stylesheet">

<script type="text/javascript">
	//全局变量
	var GV = {
		DIMAUB : "${ctx}",
		JS_ROOT : "/media/"
	};
</script>
<script src="${ctx}/media/res/jquery-1.8.2.min.js?v=${version}"></script>
<link href="${ctx}/media/artDialog/skins/default.css" type="text/css"
	rel="stylesheet" />
<script src="${ctx}/media/artDialog/artDialog.js?v=${version}"></script>
<script src="${ctx}/media/artDialog/iframeTools.js?v=v1.0"></script>

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
		      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
		      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
		    <![endif]-->

<style>
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}
</style>
</head>

<body>

	<div class="container">
		<h2 class="form-signin-heading">Please sign in</h2>
		<input type="text" id="loginName" class="form-control"
			placeholder="用户名" required autofocus> <input type="password"
			id="passwd" class="form-control" placeholder="密码" required>

		<button class="btn btn-primary" id="submit">Sign in</button>
	</div>
	<script>
		$(function() {
			jQuery("#submit").click(function() {
				if (jQuery("#loginName").val() == "") {
					return;
				} else if (jQuery("#passwd").val() == "") {
					return;
				} else {
					commit();
				}

			});

			document.onkeydown = function(event) {
				e = event ? event : (window.event ? window.event : null);
				if (e.keyCode == 13) {
					if (jQuery("#loginName").val() != ""
							&& jQuery("#passwd").val() != "") {
						commit();
					}
				}
			};
			function commit() {
				$.post("${ctx}/admin/dologin", {
					"loginName" : jQuery("#loginName").val(),
					"passwd" : jQuery("#passwd").val()
				}, function(json) {
					if (json.success) {

						window.location.href = "${ctx}/admin/";
					} else {
						art.dialog.alert(json.msg);
					}
				});
			}
		});
	</script>
</body>
</html>