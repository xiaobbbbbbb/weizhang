<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/list_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/scheduleInfo/">任务类型管理</a></li>
				<li><a href="${ctx}/scheduleInfo/saveUpdateUI">添加</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/scheduleInfo/">
			<div class="mb15">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<!-- 如果是组合查询 -->
							<table>
								<tr>
									<td><span>任务名称</span>：</td>
									<td><input id="name" class="input_text" type="text" name="name" value="${param.name}" /></td>
								</tr>
							</table>
						</td>
						<td width="60px"><button class="ace_btn btn_submit" type="submit">提交</button></td>
					</tr>
				</table>
			</div>
			<div class="table_list">
				<table>
					<thead>
						<tr>
							<th align="center" width="50px">操作</th>
							<th>任务名称</th>
							<th>表达式的值</th>
							<th>状态</th>
							<th>创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td align="center"><div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										<a href="${ctx}/scheduleInfo/saveUpdateUI?id=${dto.id}" class="green"> <i class="icon-pencil bigger-130"></i></a> <a
											href="${ctx}/scheduleInfo/delete?ids=${dto.id}" class="red J_ajax_del"><i class="icon-trash bigger-130"></i></a>
									</div></td>
								<td>${dto.name}</td>
								<td>${dto.value}</td>
								<td><c:if test="${dto.status==1}">有效</c:if> <c:if test="${dto.status==0}">无效</c:if></td>
								<td align="center"><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<jsp:include page="../../common/pager.jsp" />
			</div>
		</form>
	</div>
</body>
</html>