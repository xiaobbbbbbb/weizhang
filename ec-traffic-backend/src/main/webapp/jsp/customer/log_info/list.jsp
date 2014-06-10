<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/logInfo/">系统日志</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/logInfo/">
			<div class="mb10">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<table>
								<tr>
									<td class="field_title">操作员：</td>
									<td><input id="operatorName" class="input length_2" type="text" name="operatorName" value="${param.operatorName}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">创建时间：</td>
									<td><input id="beginCreateTime" class="input length_2 J_date" type="text" name="beginCreateTime" value="${param.beginCreateTime}" /> ~
										<input id="endCreateTime" type="text" class="input length_2 J_date" name="endCreateTime" value="${param.endCreateTime}" /></td>
								</tr>
							</table>
						</td>
						<td width="60px" class="search_btn"><button class="ace_btn btn_submit J_ajax_search_btn" type="submit">查询</button></td>
					</tr>
				</table>
			</div>
			<div class="table_list">
				<table>
					<thead>
						<tr>
							<th>操作员</th>
							<th>操作类型</th>
							<th>日志内容</th>
							<th>备注</th>
							<th>创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td>${dto.operatorName}</td>
								<td>${dto.operatorType}</td>
								<td>${dto.content}</td>
								<td>${dto.remark}</td>
								<td align="center" width="80px"><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<jsp:include page="../../common/pager.jsp" />
		</form>
	</div>
</body>
</html>