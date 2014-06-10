<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/list_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/queryAccount/spiBatchAccount/">实时查询对账</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/queryAccount/spiAccount/">
			<div class="mb15">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<!-- 如果是组合查询 -->
							<table>
								<tr>
									<td><span>客户名称</span>：</td>
									<td><input id="name" type="text" name="name" value="${param.name}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td><span>查询时间</span>：</td>
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
							<th>客户名称</th>
							<th>查询次数</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td>${dto.name}</td>
								<td>${dto.num}</td>
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