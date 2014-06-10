<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/list_header.jsp" />

<body class="J_scroll_fixed">
	<div class="nav">
		<ul class="cc">
			<li class="current"><a href="${ctx}/orgInfo/">客户信息管理</a></li>
			<li><a href="${ctx}/orgInfo/saveUpdateUI">添加</a></li>
		</ul>
	</div>
	<div class="common-form">
		<form method="get" class="J_ajaxForm" action="${ctx}/orgInfo/">
			<div class="table_list">
				<table>
					<thead>
						<tr>
							<td align="center">操作</td>
							<td align="center">序号</td>
							<td align="center">发展日期</td>
							<td>客户名称</td>
							<td>查询方式</td>
							<td>违章查询区域</td>
							<td>授权车辆数</td>
							<td>已用车辆数</td>
							<td>授权查询次数</td>
							<td>API</td>
							<td>任务类型</td>
							<td>省份</td>
							<td>城市</td>
							<td>所属集团</td>
							<td>联系人</td>
							<td>联系电话</td>
							<td>地址</td>
							<td>业务员</td>
							<td>合同到期日</td>
							<td>是否到期</td>
							<td>状态</td>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list }" var="dto" varStatus="sn">
							<tr>
								<td align="center">
									<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										<a href="${ctx}/orgInfo/saveUpdateUI?id=${dto.id}" class="green"> <i class="icon-pencil bigger-130"></i></a>
										<a href="${ctx}/orgInfo/delete?ids=${dto.id}" class="red J_ajax_del"><i class="icon-trash bigger-130"></i></a>
									</div>
								</td>
								<td>${sn.count}</td>
								<td align="center"><fmt:formatDate value="${dto.joinTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${dto.name}</td>
								<td></td>
								<td></td>
								<td>${dto.maxCarCounts}</td>
								<td>${dto.leftCarCounts}</td>
								<td>${dto.maxQueryCounts}</td>
								<td>${dto.leftQueryCounts}</td>
								<td>${dto.name}</td>
								<td>${dto.name}</td>
								<td>${dto.name}</td>
								<td>${dto.name}</td>
								<td>${dto.name}</td>
								<td>${dto.name}</td>
								<td>${dto.name}</td>
								<td>${dto.name}</td>
								<td><fmt:formatDate value="${dto.expiredTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${dto.name}</td>
								<td>${dto.name}</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="p5">
					<jsp:include page="../../common/pager.jsp" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>