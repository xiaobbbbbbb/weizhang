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
		<form class="well form-inline" action="${ctx}/traffic/detail" method="post">
					<input type="hidden" id="page" name="page" value="${param.page}" />
		
			<div class="table_list">
				<table>
					<thead>
						<tr>
							<td>车牌号</td>
							<td>车架号</td>
							<td>引擎号</td>
							<td>证书号</td>
							<td>车辆类型</td>
							<td>查询时间</td>
							<td>违章数量</td>
							<td>异常信息</td>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list }" var="dto" varStatus="sn">
							<tr>
								<td>${dto.carNo}</td>
								<td>${dto.carFrameNo}</td>
								<td>${dto.carEngineNo}</td>
								<td>${dto.carCertificate}</td>
								<td><c:choose><c:when test='${dto.carType==1}'>大型车</c:when><c:when test='${dto.carType==2}'>小型车</c:when></c:choose> </td>
								<td align="center"><fmt:formatDate value="${dto.queryTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${dto.trafficCounts}</td>
								<td>${dto.errorMessage}</td>
							</tr>
						</c:forEach>
						<c:if test="${fn:length(ECPage.list)<=0 }">
							<tr><td colspan="8" align="center">没有数据</td></tr>
						</c:if>
					</tbody>
				</table>
			
				<div class="p5">
					<jsp:include page="../common/pager.jsp" />
				</div>
			</div>
	</form>
	</div>
</body>
<script>

$(function(){
	 $("#startTime,#endTime").datepickerStyle();
	 $("#search_btn").click(function(){
		$("from[0]").submit();				 
	 });
});


function clean(){
	$("#batNo,#carNo,#endTime,#startTime").val("");
}
</script>
</html>

