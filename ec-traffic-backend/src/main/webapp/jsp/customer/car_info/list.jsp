<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/carInfo/">车辆信息</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/carInfo/">
			<div class="mb10">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<!-- 如果是组合查询 -->
							<table>
								<tr>
									<td class="field_title"><span>车牌号</span>：</td>
									<td><input id="carNo" class="input length_2" type="text" name="carNo" value="${param.carNo}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title"><span>车架号</span>：</td>
									<td><input id="carFrameNo" class="input length_2" type="text" name="carFrameNo" value="${param.carFrameNo}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title"><span>车辆证书编号</span>：</td>
									<td><input id="carCertificate" class="input length_2" type="text" name="carCertificate" value="${param.carCertificate}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">是否有效：</td>
									<td><select name="status">
											<option value="">-请选择-</option>
											<option value="1" <c:if test="${param.status==1}">selected="selected"</c:if>>有效</option>
											<option value="0" <c:if test="${param.status==0}">selected="selected"</c:if>>无效</option>
									</select></td>
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
							<th>车牌号</th>
							<th>车架号</th>
							<th>发动机号</th>
							<th>车辆证书编号</th>
							<th>车主姓名</th>
							<th>车辆类型</th>
							<th>创建时间</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td>${dto.carNo}</td>
								<td>${dto.carFrameNo}</td>
								<td>${dto.carEngineNo}</td>
								<td>${dto.carCertificate}</td>
								<td>${dto.ownerName}</td>
								<td align="center"><c:if test="${dto.carType==1}">大型</c:if> <c:if test="${dto.carType==0}">小型</c:if></td>
								<td align="center" width="80px"><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center" width="60px"><c:if test="${dto.status==1}">有效</c:if> <c:if test="${dto.status==0}">无效</c:if></td>
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