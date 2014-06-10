<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/list_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href="${ctx}/queryInfo/">接口调用记录</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/queryInfo/">
			<div class="mb15">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<!-- 如果是组合查询 -->
							<table>
								<tr>
									<td class="field_title">车主姓名：</td>
									<td class="field_input"><input id="ownerName" class="input_text" type="text" name="ownerName" value="${param.ownerName}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">查询时间：</td>
									<td class="field_input decimal"><input id="beginQueryTime" type="text" name="beginQueryTime" value="${param.beginQueryTime}" />~<input
										id="endQueryTime}" type="text" name="endQueryTime}" value="${param.endQueryTime}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">车牌号：</td>
									<td class="field_input"><input id="carNo" class="input_text" type="text" name="carNo" value="${param.carNo}" /></td>
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
							<th>车主姓名</th>
							<th>机构编码</th>
							<th>车牌号</th>
							<th>车辆类型</th>
							<th>车架号</th>
							<th>发动机号</th>
							<th>查询时间(接口调用时间)</th>
							<th>查询耗时(单位毫秒)</th>
							<th>车辆登记证书</th>
							<th>任务ID</th>
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
								<td>${dto.orgCode}</td>
								<td>${dto.carNo}</td>
								<td>${dto.carType}</td>
								<td>${dto.carFrameNo}</td>
								<td>${dto.carEngineNo}</td>
								<td align="center"><fmt:formatDate value="${dto.queryTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${dto.costTime}</td>
								<td>${dto.carCertificate}</td>
								<td>${dto.taskId}</td>
								<td>${dto.errorCode}</td>
								<td>${dto.trafficCounts}</td>
								<td>${dto.errorMessage}</td>
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