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

<body class="J_scroll_fixed">
	<div class="common-form">
		
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
						<c:forEach items="${list }" var="dto" varStatus="sn">
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
			
			</div>
	</div>
</body>
<script>

</script>
</html>

