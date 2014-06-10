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
	<div class="common-form">
		<form class="well form-inline" action="${ctx}/queryAccount/batCars" method="post">
			<label>车牌号：</label><input type="text" id="carNo" name="carNo"
				value="${dto.carNo }" /> <label>车辆类型：</label><select id="carType"
				name="carType" class="length_2">
				<option value="0">所有</option>
				<option value="1"
					<c:if test="${dto.carType==1 }"> selected="true"</c:if>>大型车</option>
				<option value="2"
					<c:if test="${dto.carType==2 }"> selected="true"</c:if>>小型车</option>
			</select> <label>批次：</label><input type="text" id="batNo" name="batNo"
				value="${dto.batNo }" /> 
			查询时间：	
				<input type="text" id="startTime"
				name="startTime" class="input-small" style="cursor: pointer;"
				readonly="readonly" value="${dto.startTime}">~
			<input type="text" id="endTime" name="endTime" class="input-small"
				style="cursor: pointer;" readonly="readonly" 
				value="${dto.endTime}"> <input type="hidden" id="org"
				name="orgScheduleId" value="${orgScheduleId}" />
			<button class="btn" id="search_btn">搜索</button>
			<button class="btn" onclick="clean()">清空</button>
			<input type="hidden" id="page" name="page" value="1" />
		</form>
		<div class="table_list">
			<table>
				<thead>
					<tr>
						<td align="center">序号</td>
						<td>车牌号</td>
						<td>车架号</td>
						<td>引擎号</td>
						<td>证书号</td>
						<td>车辆类型</td>
						<td>查询类型</td>
						<td>批次</td>
						<td>状态</td>
						<td>任务名称</td>
						<td>绑定日期</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ECPage.list }" var="dto" varStatus="sn">
						<tr>
							<td>${sn.count}</td>
							<td>${dto.carNo}</td>
							<td>${dto.carFrameNo}</td>
							<td>${dto.carEngineNo}</td>
							<td>${dto.certificate}</td>
							<td><c:choose>
									<c:when test='${dto.carType==1}'>大型车</c:when>
									<c:when test='${dto.carType==2}'>小型车</c:when>
								</c:choose></td>
							<td><c:choose>
									<c:when test='${dto.queryType==1}'>按次</c:when>
									<c:when test='${dto.queryType==2}'>包年</c:when>
								</c:choose></td>
							<td>${dto.batNo}</td>
							<td>
							<c:choose>
									<c:when test='${dto.status==0}'>未绑定</c:when>
									<c:when test='${dto.status==1}'>已绑定</c:when>
								</c:choose></td>
								<td>${dto.name}</td>
							<td align="center"><fmt:formatDate value="${dto.taskDate}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>

						</tr>
					</c:forEach>
					<c:if test="${fn:length(ECPage.list)<=0 }">
						<tr>
							<td colspan="10" align="center">没有数据</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<div class="p5">
				<jsp:include page="../common/pager.jsp" />
			</div>
		</div>
	
	</div>
</body>
<script>
$(function(){
	 $("#startTime,#endTime").datepickerStyle();
	 $("#search_btn").click(function(){
		$("form[0]").submit();				 
	 });
});

function clean(){
	$("#batNo,#carNo,#endTime,#startTime").val("");
}
</script>
</html>
