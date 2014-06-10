<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href="${ctx}/spiQuery/">接口调用记录</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/spiQuery/">
			<div class="mb10">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<!-- 如果是组合查询 -->
							<table>
								<tr>
									<td class="field_title">车主姓名：</td>
									<td><input id="ownerName" class="input length_2" type="text" name="ownerName" value="${param.ownerName}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">车牌号：</td>
									<td class="field_input"><input id="carNo" class="input length_2" type="text" name="carNo" value="${param.carNo}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">调用时间：</td>
									<td class="field_input decimal"><input id="beginQueryTime" class="input length_2 J_date" type="text" name="beginQueryTime"
										value="${param.beginQueryTime}" />~<input id="endQueryTime" type="text" class="input length_2 J_date" name="endQueryTime"
										value="${param.endQueryTime}" /></td>
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
							<th>车主姓名</th>
							<th>所属客户</th>
							<th>车牌号</th>
							<th>车辆类型</th>
							<th>车架号</th>
							<th>发动机号</th>
							<th>接口调用时间</th>
							<th>查询耗时(毫秒)</th>
							<th>车辆登记证书</th>
							<th>任务名称</th>
							<th>错误代号</th>
							<th>违章记录条数</th>
							<th>错误信息</th>
							<th>创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td>${dto.ownerName}</td>
								<td>${dto.orgName}</td>
								<td>${dto.carNo}</td>
								<td align="center"><c:if test="${dto.carType==1}">大型</c:if> <c:if test="${dto.carType==0}">小型</c:if></td>
								<td>${dto.carFrameNo}</td>
								<td>${dto.carEngineNo}</td>
								<td align="center"><fmt:formatDate value="${dto.queryTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${dto.costTime}</td>
								<td>${dto.carCertificate}</td>
								<td>${dto.taskName}</td>
								<td>${dto.errorCode}</td>
								<td>${dto.trafficCounts}</td>
								<td>${dto.errorMessage}</td>
								<td align="center"><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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