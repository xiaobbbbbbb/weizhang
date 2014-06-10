<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
<title></title>

<link href="${ctx}/media/bootstrap/bootstrap.min.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/css/font-awesome.min.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/ace/css/ace.min.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/res/index/admin.css?v=${version}" rel="stylesheet" />
<script type="text/javascript">
	//全局变量
	var GV = {
		DIMAUB : "${ctx}",
		JS_ROOT : "/media/"
	};
</script>
<script src="${ctx}/media/res/jquery-1.8.2.min.js?v=${version}"></script>
<link href="${ctx}/media/jquery-ui-1.9.2.custom/development-bundle/themes/base/jquery.ui.all.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/media/artDialog/skins/default.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/media/jquery-ui-1.9.2.custom/jquery-ui-1.9.2.custom.js"></script>
<script src="${ctx}/media/artDialog/artDialog.js?v=${version}"></script>
<script src="${ctx}/media/artDialog/iframeTools.js?v=v1.0"></script>
<script src="${ctx}/media/ec/js/jquery.ec-base.js?v=v1.0"></script>


<body class="J_scroll_fixed">
	<div class="common-form">
		<form class="well form-inline" action="${ctx}/traffic/bat_items" method="post">
					<label>车牌号：</label><input type="text" id="carNo" name="carNo" value="${dto.carNo }"/> 
					<button class="btn" id="search_btn">搜索</button>
					<input type="hidden" id="page" name="page" value="${param.page}" />
					<input type="hidden" id="taskId" name="taskId" value="${taskId}" />
					<input type="hidden" id="startTime" name="startTime" value="${dto.startTime}" />
					<input type="hidden" id="endTime" name="endTime" value="${dto.endTime}" />
			</form>
			<div class="table_list">
				<table>
					<thead>
						<tr>
							<td align="center">车牌号</td>
							<td align="center">违章详情</td>
							<td align="center">违章城市</td>
							<td align="center">违章地点</td>
							<td align="center">违章时间</td>
							<td align="center">扣分</td>
							<td align="center">罚款</td>
							<td align="center">是否处理</td></tr>
							
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list }" var="dto" varStatus="sn">
							<tr>
								<td align="center">${dto.carNo}</td>
								<td align="center">${dto.trafficDetail}</td>
								<td align="center">${dto.trafficCity}</td>
								<td align="center">${dto.trafficLocation}</td>
								<td align="center"><fmt:formatDate value="${dto.trafficTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center">${dto.scores}</td>
								<td align="center">${dto.money}</td>
								<td align="center"><c:choose><c:when test='${dto.status==0}'>未处理</c:when><c:when test='${dto.carType==1}'>已处理</c:when></c:choose> </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="p5">
					<jsp:include page="../common/pager.jsp" />
				</div>
			</div>
	</div>
</body>
</html>

