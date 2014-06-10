<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/list_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/queryTask/">批查任务</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/queryTask/">
			<div class="mb15">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<!-- 如果是组合查询 -->
							<table>
								<tr>
									<td><span>机构编号</span>：</td>
									<td><input id="orgCode" class="input_text" type="text" name="orgCode" value="${param.orgCode}" /></td>
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
							<th>机构编号</th>
							<th>操作员</th>
							<th>更新时间</th>
							<th>创建时间</th>
							<th>任务状态</th>
							<th>任务类型</th>
							<th>任务描述</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td>${dto.orgCode}</td>
								<td>${dto.operatorId}</td>
								<td align="center"><fmt:formatDate value="${dto.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center"><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${dto.status}</td>
								<td>${dto.taskType}</td>
								<td>${dto.taskDetail}</td>
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