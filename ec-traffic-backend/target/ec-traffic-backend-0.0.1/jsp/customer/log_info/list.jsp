<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/list_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/logInfo/">系统日志</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/logInfo/">
			<div class="mb15">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<!-- 如果是组合查询 -->
							<table>
								<tr>
									<td><span>操作员</span>：</td>
									<td><input id="beginOperatorId" type="text" name="beginOperatorId" value="${param.beginOperatorId}" /> ~ <input id="endOperatorId"
										type="text" name="endOperatorId" value="${param.endOperatorId}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td><span>创建时间</span>：</td>
									<td><input id="beginCreateTime" type="text" name="beginCreateTime" value="${param.beginCreateTime}" /> ~ <input id="endCreateTime"
										type="text" name="endCreateTime" value="${param.endCreateTime}" /></td>
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
								<td>${dto.operatorId}</td>
								<td>${dto.operatorType}</td>
								<td>${dto.content}</td>
								<td>${dto.remark}</td>
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