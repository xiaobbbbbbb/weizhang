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
		<form class="well form-inline" action="${ctx}/traffic/result" method="post">
					<label>任务名称：</label><input type="text" id="name" name="name" value="${dto.name }"/> 
					查询时间：<input type="text" id="startTime" name="startTime" class="input-small" style="cursor: pointer;" readonly="readonly"
						 value="${dto.startTime}">~<input type="text" id="endTime" name="endTime"
						class="input-small" style="cursor: pointer;" readonly="readonly"  value="${dto.endTime}">
						<input type="hidden" id="org" name="orgScheduleId" value="${orgScheduleId}"/>
					<button class="btn" id="search_btn">搜索</button>
					<button class="btn" onclick="clean()">清空</button>
					<input type="hidden" id="page" name="page" value="1" />
		</form>
			<div class="table_list">
				<table>
					<thead>
						<tr>
							<td align="center">任务名称</td>
							<td align="center">违章数量</td>
							<td align="center">有效数量</td>
							<td align="center">总量</td>
							<td align="center">失败数量</td>
							<td align="center">起始时间</td>
							<td align="center">结束时间</td>
							<td align="center">详情</td>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list }" var="dto" varStatus="sn">
							<tr>
								<td align="center">${dto.name}</td>
								<td align="center"><c:choose ><c:when test="${dto.trafficCount>0}"><a style="text-decoration:underline;" href="javascript:showTraffics(${dto.queryTaskId},'${dto.startTime}','${dto.endTime}');">${dto.trafficCount}</a></c:when><c:otherwise> ${dto.trafficCount}</c:otherwise></c:choose></td>
								<td align="center">${dto.validCount}</td>
								<td align="center">${dto.totalCount}</td>
								<td align="center">${dto.failCount}</td>
								<td align="center"><fmt:formatDate value="${dto.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center"><fmt:formatDate value="${dto.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center"><a href="javascript:showDialog(${dto.queryTaskId},'${dto.startTime}','${dto.endTime}');" class="green"> <i class="icon-list bigger-130"></i>
										</a></td>
							</tr>
						</c:forEach>
						<c:if test="${fn:length(ECPage.list)<=0 }">
							<tr><td colspan="10" align="center">没有数据</td></tr>
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


function clean(){
	$("#name,#endTime,#startTime").val("");
}

function showDialog(taskId,startTime,endTime){	
	//防止重复弹框
	if(art.dialog.list[123]){
		art.dialog.list[123].close();
	}
	var url ="${ctx}/traffic/detail?taskId="+taskId+"&startTime="+startTime+"&endTime="+endTime;
	
	art.dialog.open(url, {
		id:123,
	    width: 800,
	    height: 600,
        title:'查询详细'
    }); 
}

function showTraffics(taskId,startTime,endTime){
	//防止重复弹框
	if(art.dialog.list[234]){
		art.dialog.list[234].close();
	}
	var url ="${ctx}/traffic/bat_items?taskId="+taskId+"&startTime="+startTime+"&endTime="+endTime;
	art.dialog.open(url, {
		id:234,
	    width: 800,
	    height: 600,
        title:'违章明细'
    }); 
}
</script>
</html>

