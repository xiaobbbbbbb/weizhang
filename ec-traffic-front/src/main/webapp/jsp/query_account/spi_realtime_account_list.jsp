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
<link href="${ctx}/media/css/font-awesome.min.css?v=${version}"
	rel="stylesheet">
<link href="${ctx}/media/ace/css/ace.min.css?v=${version}"
	rel="stylesheet">
<link href="${ctx}/media/res/index/admin.css?v=${version}"
	rel="stylesheet" />
<script type="text/javascript">
	//全局变量
	var GV = {
		DIMAUB : "${ctx}",
		JS_ROOT : "/media/"
	};
</script>
<script src="${ctx}/media/res/jquery-1.8.2.min.js?v=${version}"></script>
<link
	href="${ctx}/media/jquery-ui-1.9.2.custom/development-bundle/themes/base/jquery.ui.all.css"
	type="text/css" rel="stylesheet" />
<link href="${ctx}/media/artDialog/skins/default.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/media/jquery-ui-1.9.2.custom/jquery-ui-1.9.2.custom.js"></script>
<script src="${ctx}/media/artDialog/artDialog.js?v=${version}"></script>
<script src="${ctx}/media/artDialog/iframeTools.js?v=v1.0"></script>
<script src="${ctx}/media/ec/js/jquery.ec-base.js?v=v1.0"></script>
<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/queryAccount/realTime/">实时查询对账</a></li>
			</ul>
		</div>
		<div class="common-form">
			<form class="well form-inline" action="${ctx}/queryAccount/realTime/"
				method="post">
				查询时间：<input type="text" id="beginQueryTime"
					name="beginQueryTime" class="input-small" style="cursor: pointer;"
					readonly="readonly" 
					value="${dto.startTime}">~<input type="text"
					id="endQueryTime" name="endQueryTime" class="input-small"
					style="cursor: pointer;" readonly="readonly" 
					value="${dto.endTime}">
				<button class="btn" id="search_btn">搜索</button>
				<button class="btn" onclick="clean()">清空</button>
				<input type="hidden" id="page" name="page" value="1" />
				</form>
			<div class="table_list">
				<table>
					<thead>
						<tr>
							<td>查询次数</td>
						</tr>
					</thead>
					<tbody>
							<tr>
								<td ><c:choose ><c:when test="${times>0}"><a style="text-decoration:underline;" href="javascript:showQueryDialog();">${times}</a></c:when><c:otherwise> ${times}</c:otherwise></c:choose></td>
							</tr>
					</tbody>
				</table>
			</div>
	</div>
</div>
	<script>
		$(function() {
			$("#beginQueryTime,#endQueryTime").datepickerStyle();
			$("#search_btn").click(function() {
				$("form[0]").submit();
			});
		});

		function clean() {
			$("#name,#beginCreateTime,#endCreateTime").val("");
		}
		
		function showQueryDialog(){
			
			var startTime = $("#beginQueryTime").val();
			var endTime = $("#endQueryTime").val();
			window.location.href ="${ctx}/queryAccount/realTimeDetial?startTime="+startTime+"&endTime="+endTime;
		}
	</script>
</body>
</html>