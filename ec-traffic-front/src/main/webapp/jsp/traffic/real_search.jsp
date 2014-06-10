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
<style>
	.table_list td{
		text-align:center;
	}
</style>
</head>
<body class="J_scroll_fixed">
	<div class="common-form">
			<form class="well form-inline" action="${ctx}/traffic/realTime" method="post">
					<label>车牌号：</label><input type="text" id="carNo" name="carNo" value="${dto.carNo }"/> 
					查询时间：<input type="text" id="startTime" name="startTime" class="input-small" style="cursor: pointer;" readonly="readonly"
						 value="${dto.startTime}"> ~<input type="text" id="endTime" name="endTime"
						class="input-small" style="cursor: pointer;" readonly="readonly"  value="${dto.endTime}">
						<input type="hidden" id="org" name="orgScheduleId" value="${orgScheduleId}"/>
					<button class="btn" id="search_btn">搜索</button>
					<button class="btn" onclick="clean()">清空</button>
					<input type="hidden" id="page" name="page" value="${param.page}" />
			</form>
		
			<div class="table_list">
			
				<table>
					<thead>
						<tr>
							<td>车牌号</td>
							<td>违章数量</td>
							<td>车架号</td>
							<td>引擎号</td>
							<td>证书号</td>
							<td>车辆类型</td>
							<td>查询时间</td>
							<td>异常信息</td>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list }" var="dto" varStatus="sn">
							<tr >
							
								<td> ${dto.carNo}</td>
								<td align="center"><c:choose ><c:when test="${dto.trafficCounts>0}"><a style="text-decoration:underline;" href="javascript:showDialog(${dto.id});">${dto.trafficCounts}</a></c:when><c:otherwise> ${dto.trafficCounts}</c:otherwise></c:choose></td>
								<td>${dto.carFrameNo}</td>
								<td>${dto.carEngineNo}</td>
								<td>${dto.carCertificate}</td>
								<td><c:choose><c:when test='${dto.carType==1}'>大型车</c:when><c:when test='${dto.carType==2}'>小型车</c:when></c:choose> </td>
								<td align="center"><fmt:formatDate value="${dto.queryTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
	</div>
</body>
<script>

$(function(){
	 $("#startTime,#endTime").datepickerStyle();
	 $("#search_btn").click(function(){
		$("from[0]").submit();				 
	 });
	 
	 
});
function showDialog(id){	
	//防止重复弹框
	 if(art.dialog.list[123]){
		art.dialog.list[123].close();
	} 
	var url ="${ctx}/traffic/traffic_items?queryId="+id;
	
	art.dialog.open(url, {
		id:123,
	    width: 800,
	    height: 400,
        title:'违章信息',
        close:function(){
        }
    }); 
}

function clean(){
	$("#batNo,#carNo,#endTime,#startTime").val("");
}
</script>
</html>

