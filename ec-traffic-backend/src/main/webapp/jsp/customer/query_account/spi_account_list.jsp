<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/queryAccount/spiAccount/">来源接口对账</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/queryAccount/spiAccount/">
			<div class="mb10">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<!-- 如果是组合查询 -->
							<table>
								<tr>
									<td class="field_title">接口名称：</td>
									<td><input id="name" type="text" class="input length_2" name="name" value="${param.name}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">查询时间：</td>
									<td><input id="beginCreateTime" type="text" class="input length_2 J_date" name="beginCreateTime" value="${param.beginCreateTime}" /> ~
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
							<th>接口名称</th>
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
			</div>
			<jsp:include page="../../common/pager.jsp" />
		</form>
	</div>
</body>
</html>