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

<link href="${ctx}/media/artDialog/skins/default.css" type="text/css" rel="stylesheet" />

<script src="${ctx}/media/artDialog/artDialog.js?v=${version}"></script>
<script src="${ctx}/media/artDialog/iframeTools.js?v=v1.0"></script>
<body class="J_scroll_fixed">
	<div class="nav">
		<ul class="cc">
			<li class="current"><a href="${ctx}/traffic/taskUI">任务列表</a></li>
		</ul>
	</div>
	<div class="common-form">
		<form method="get" class="J_ajaxForm">
			<div class="table_list">
				<table>
					<thead>
						<tr>
							<td align="center">操作</td>
							<td align="center">任务名称</td>
							<td align="center">数量</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="dto" varStatus="sn">
							<tr>
								<td align="center">
									<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										<a href="javascript:showDialog(${dto.id},'add');" class="green"> <i class="icon-plus bigger-130"></i>
										</a>
										<a href="javascript:showDialog(${dto.id},'del');" class="green"> <i class="icon-pencil bigger-130"></i>
										</a>
									</div>
								</td>
								<td align="center">${dto.name}</td>
								<td align="center"><c:choose ><c:when test="${dto.nums>0}"><a style="text-decoration:underline;" href="javascript:showDialog(${dto.id},'del');">${dto.nums}</a></c:when><c:otherwise> ${dto.nums}</c:otherwise></c:choose></td>
								
							</tr>
						</c:forEach>
						<c:if test="${fn:length(list)<=0 }">
							<tr><td colspan="10" align="center">没有数据</td></tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</form>
	</div>
</body>
</html>

<script>

function showDialog(id,operate){	
	//防止重复弹框
	 if(art.dialog.list[123]){
		art.dialog.list[123].close();
	} 
	var url ="";
	var title = "";
	if(operate=='add'){
		url ="${ctx}/traffic/select?orgScheduleId="+id;
		title="绑定车辆" ;
	}if(operate=='del'){
		url ="${ctx}/traffic/delUI?orgScheduleId="+id;
		title="解绑车辆";
	}
	art.dialog.open(url, {
		id:123,
	    width: 800,
	    height: 600,
        title:title,
        close:function(){
        	document.location.reload();
        }
    }); 
}

</script>