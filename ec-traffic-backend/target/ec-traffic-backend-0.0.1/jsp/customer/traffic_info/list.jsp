<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/list_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/trafficInfo/">违章明细</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/trafficInfo/">
			<div class="mb15">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<!-- 如果是组合查询 -->
							<table>
								<tr>
									<td><span>车牌号</span>：</td>
									<td><input id="carNo" class="input_text" type="text" name="carNo" value="${param.carNo}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td><span>违章代号</span>：</td>
									<td><input id="code" class="input_text" type="text" name="code" value="${param.code}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td><span>文书号</span>：</td>
									<td><input id="archive" class="input_text" type="text" name="archive" value="${param.archive}" /></td>
								</tr>
							</table>
						</td>
						<td><button class="ace_btn btn_submit" type="submit">提交</button></td>
					</tr>
				</table>
			</div>
			<div class="table_list">
				<table>
					<thead>
						<tr>
							<th>车牌号</th>
							<th>车型</th>
							<th>违章时间</th>
							<th>违章地点</th>
							<th>违章描述</th>
							<th>违章扣分</th>
							<th>违章代号</th>
							<th>创建时间</th>
							<th>文书号</th>
							<th>违章罚款</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td>${dto.carNo}</td>
								<td>${dto.carType}</td>
								<td align="center"><fmt:formatDate value="${dto.trafficTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${dto.trafficLocation}</td>
								<td>${dto.trafficDetail}</td>
								<td>${dto.scores}</td>
								<td>${dto.code}</td>
								<td align="center"><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${dto.archive}</td>
								<td>${dto.money}</td>
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